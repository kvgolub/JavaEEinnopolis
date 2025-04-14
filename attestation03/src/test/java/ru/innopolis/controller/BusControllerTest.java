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
import ru.innopolis.controller.impl.BusControllerImpl;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.service.impl.BusServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {BusControllerImpl.class})
public class BusControllerTest {

    @MockitoBean
    private BusServiceImpl busService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusControllerTest() throws Exception {
        Mockito.when(busService.createBus(Mockito.any(BusRequest.class))).thenReturn(busResponse);

        mvc.perform(post("/api/v1/bus_station/bus")
                        .contentType("application/json")
                        .content(busRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusControllerTest() throws Exception {
        Mockito.when(busService.findByIdBus(Mockito.any(Long.class))).thenReturn(Optional.of(busResponse));

        mvc.perform(get("/api/v1/bus_station/bus/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(busResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusesControllerTest() throws Exception {
        Mockito.when(busService.findAllBuses()).thenReturn(busResponseAll);

        mvc.perform(get("/api/v1/bus_station/bus/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusControllerTest() throws Exception {
        Mockito.when(busService.updateBus(Mockito.any(Long.class), Mockito.any(BusRequest.class))).thenReturn(Optional.of(busResponseNew));

        mvc.perform(put("/api/v1/bus_station/bus/1")
                        .contentType("application/json")
                        .content(busRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(busResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusControllerTest() throws Exception {
        Mockito.when(busService.deleteByIdBus(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/bus/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusesControllerTest() throws Exception {
        Mockito.when(busService.deleteAllBuses()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/bus/all").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileBusesControllerTest() throws Exception {
        Mockito.when(busService.uploadFileBuses(Mockito.any(MultipartFile.class))).thenReturn(true);

        mvc.perform(multipart("/api/v1/bus_station/bus/file")
                .file(busFile)
                .with(csrf())
                )
                .andExpect(status().isCreated());
    }


    // create
    private final BusResponse busResponse = new BusResponse(1L, 1L, "Газель", "a001aa", 20, 1000000.00);
    private final String busRequestBody = """
                            {
                            	"type": 1,
                                "model": "Газель",
                                "regNumber": "a001aa",
                                "passengerCapacity": 20,
                                "price": 1000000.00
                            }
                        """;

    // find
    private final String busResponseJson = """
                            {
                            	"id": 1,
                            	"type": 1,
                                "model": "Газель",
                                "regNumber": "a001aa",
                                "passengerCapacity": 20,
                                "price": 1000000.00
                            }
                        """;

    // findAll
    private final List<BusResponse> busResponseAll = List.of(
            new BusResponse(1L, 1L, "Газель", "а001aa", 20, 1000000.00),
            new BusResponse(2L, 2L, "ЛиАЗ", "в002aa", 50, 1500000.05)
    );

    // update
    private final BusResponse busResponseNew = new BusResponse(1L, 4L, "Икарус", "м055ма", 200,3000000.00);
    private final String busRequestNewBody = """
                            {
                                "type": 4,
                                "model": "Икарус",
                                "regNumber": "м055ма",
                                "passengerCapacity": 200,
                                "price": 3000000.00
                            }
                        """;
    private final String busResponseNewJson = """
                            {
                                "id": 1,
                                "type": 4,
                                "model": "Икарус",
                                "regNumber": "м055ма",
                                "passengerCapacity": 200,
                                "price": 3000000.00
                            }
                        """;

    // uploadFile
    private final MockMultipartFile busFile = new MockMultipartFile(
            "file",
            "busesRequest.json",
            "application/json",
            """
                [
                    {
                        "type": 1,
                        "model": "Автобус-100",
                        "regNumber": "A001AA",
                        "passengerCapacity": 100,
                        "price": 20000000.00
                    }
                ]
            """.getBytes()
    );

}
