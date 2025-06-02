package ru.innopolis.controller.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.controller.impl.DriverControllerImpl;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.impl.DriverServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusVariables.updateRequestBody;
import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.DriverVariables.*;


@WebMvcTest(controllers = {DriverControllerImpl.class})
public class DriverControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private DriverServiceImpl driverService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createDriverControllerTest() throws Exception {
        Mockito.when(driverService.createDriver(Mockito.any(DriverRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/driver")
                        .contentType("application/json")
                        .content(updateRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdDriverControllerTest() {
        Mockito.when(driverService.findByIdDriver(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(driverService.findByIdDriver(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/driver/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/driver/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllDriversControllerTest() throws Exception {
        Mockito.when(driverService.findAllDrivers()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/driver/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllDriversReturnEmptyControllerTest() throws Exception {
        Mockito.when(driverService.findAllDrivers()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/driver/list"))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateDriverControllerTest() {
        Mockito.when(driverService.updateDriver(Mockito.eq(1L), Mockito.any(DriverRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(driverService.updateDriver(Mockito.eq(100L), Mockito.any(DriverRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/driver/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/driver/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdDriverControllerTest() {
        Mockito.when(driverService.deleteByIdDriver(1L)).thenReturn(true);
        Mockito.when(driverService.deleteByIdDriver(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/driver/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/driver/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllDriversControllerTest() throws Exception {
        Mockito.when(driverService.deleteAllDrivers()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/driver/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllDriversReturnEmptyControllerTest() throws Exception {
        Mockito.when(driverService.deleteAllDrivers()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/driver/all").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileDriversControllerTest() throws Exception {
        Mockito.when(driverService.uploadFileDrivers(Mockito.any(MultipartFile.class))).thenReturn(true);

        mvc.perform(multipart("/api/v1/bus_station/driver/file")
                        .file(uploadFile)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(true));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileDriversReturnIncorrectControllerTest() throws Exception {
        Mockito.when(driverService.uploadFileDrivers(Mockito.any(MultipartFile.class))).thenThrow(InvalidFileTypeException.class);

        mvc.perform(multipart("/api/v1/bus_station/driver/file")
                        .file(uploadFileNull)
                        .with(csrf())
                )
                .andExpect(result -> Assertions.assertInstanceOf(InvalidFileTypeException.class, result.getResolvedException()));
    }
}
