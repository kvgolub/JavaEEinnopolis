package ru.innopolis.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.BusStopControllerImpl;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;
import ru.innopolis.service.impl.BusStopServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {BusStopControllerImpl.class})
public class BusStopControllerTest {

    @MockitoBean
    private BusStopServiceImpl busStopService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusStopControllerTest() throws Exception {
        Mockito.when(busStopService.createBusStop(Mockito.any(BusStopRequest.class))).thenReturn(busStopResponse);

        mvc.perform(post("/api/v1/bus_station/bus_stop")
                        .contentType("application/json")
                        .content(busStopRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusStopControllerTest() throws Exception {
        Mockito.when(busStopService.findByIdBusStop(Mockito.any(Long.class))).thenReturn(Optional.of(busStopResponse));

        mvc.perform(get("/api/v1/bus_station/bus_stop/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(busStopResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusStopsControllerTest() throws Exception {
        Mockito.when(busStopService.findAllBusStops()).thenReturn(busStopResponseAll);

        mvc.perform(get("/api/v1/bus_station/bus_stop/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusStopControllerTest() throws Exception {
        Mockito.when(busStopService.updateBusStop(Mockito.any(Long.class), Mockito.any(BusStopRequest.class))).thenReturn(Optional.of(busStopResponseNew));

        mvc.perform(put("/api/v1/bus_station/bus_stop/1")
                        .contentType("application/json")
                        .content(busStopRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(busStopResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusStopControllerTest() throws Exception {
        Mockito.when(busStopService.deleteByIdBusStop(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/bus_stop/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusStopsControllerTest() throws Exception {
        Mockito.when(busStopService.deleteAllBusStops()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/bus_stop/all").with(csrf()))
                .andExpect(status().isOk());
    }


    // create
    private final BusStopResponse busStopResponse = new BusStopResponse(1L, "Завод Черный экран");
    private final String busStopRequestBody = """
                            {
                            	"nameStop": "Завод Черный экран"
                            }
                        """;

    // find
    private final String busStopResponseJson = """
                            {
                            	"id": 1,
                            	"nameStop": "Завод Черный экран"
                            }
                        """;

    // findAll
    private final List<BusStopResponse> busStopResponseAll = List.of(
            new BusStopResponse(1L, "Завод Черный экран"),
            new BusStopResponse(2L, "Речной вокзал")
    );

    // update
    private final BusStopResponse busStopResponseNew = new BusStopResponse(1L, "ул.Вишнёвая");
    private final String busStopRequestNewBody = """
                            {
                                "nameStop": "ул.Вишнёвая"
                            }
                        """;
    private final String busStopResponseNewJson = """
                            {
                                "id": 1,
                                "nameStop": "ул.Вишнёвая"
                            }
                        """;

}
