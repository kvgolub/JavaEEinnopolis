package ru.innopolis.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.controller.impl.DriverControllerImpl;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;
import ru.innopolis.service.impl.DriverServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {DriverControllerImpl.class})
public class DriverControllerTest {

    @MockitoBean
    private DriverServiceImpl driverService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void createDriverControllerTest() throws Exception {
        Mockito.when(driverService.createDriver(Mockito.any(DriverRequest.class))).thenReturn(driverResponse);

        mvc.perform(post("/api/v1/bus_station/driver")
                        .contentType("application/json")
                        .content(driverRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdDriverControllerTest() throws Exception {
        Mockito.when(driverService.findByIdDriver(Mockito.any(Long.class))).thenReturn(Optional.of(driverResponse));

        mvc.perform(get("/api/v1/bus_station/driver/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(driverResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllDriversControllerTest() throws Exception {
        Mockito.when(driverService.findAllDrivers()).thenReturn(driverResponseAll);

        mvc.perform(get("/api/v1/bus_station/driver/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateDriverControllerTest() throws Exception {
        Mockito.when(driverService.updateDriver(Mockito.any(Long.class), Mockito.any(DriverRequest.class))).thenReturn(Optional.of(driverResponseNew));

        mvc.perform(put("/api/v1/bus_station/driver/1")
                        .contentType("application/json")
                        .content(driverRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(driverResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdDriverControllerTest() throws Exception {
        Mockito.when(driverService.deleteByIdDriver(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/driver/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllDriversControllerTest() throws Exception {
        Mockito.when(driverService.deleteAllDrivers()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/driver/all").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileDriversControllerTest() throws Exception {
        Mockito.when(driverService.uploadFileDrivers(Mockito.any(MultipartFile.class))).thenReturn(true);

        mvc.perform(multipart("/api/v1/bus_station/driver/file")
                .file(driverFile)
                .with(csrf())
                )
                .andExpect(status().isCreated());
    }


    // create
    private final DriverResponse driverResponse = new DriverResponse(1L, "Иванов", "Иван", 50);
    private final String driverRequestBody = """
                            {
                            	"surname": "Иванов",
                                "name": "Иван",
                                "age": 50
                            }
                        """;

    // find
    private final String driverResponseJson = """
                            {
                            	"id": 1,
                            	"surname": "Иванов",
                                "name": "Иван",
                                "age": 50
                            }
                        """;

    // findAll
    private final List<DriverResponse> driverResponseAll = List.of(
            new DriverResponse(1L, "Иванов", "Иван", 50),
            new DriverResponse(2L, "Петров", "Петр", 35)
    );

    // update
    private final DriverResponse driverResponseNew = new DriverResponse(1L, "Пупкин", "Вася", 22);
    private final String driverRequestNewBody = """
                            {
                                "surname": "Пупкин",
                                "name": "Вася",
                                "age": 22
                            }
                        """;
    private final String driverResponseNewJson = """
                            {
                                "id": 1,
                                "surname": "Пупкин",
                                "name": "Вася",
                                "age": 22
                            }
                        """;

    // uploadFile
    private final MockMultipartFile driverFile = new MockMultipartFile(
            "file",
            "driversRequest.json",
            "application/json",
            """
                [
                    {
                        "surname": "Сергеев",
                        "name": "Сергей",
                        "age": 52
                    }
                ]
            """.getBytes()
    );

}
