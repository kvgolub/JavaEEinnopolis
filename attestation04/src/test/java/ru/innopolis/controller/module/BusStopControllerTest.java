package ru.innopolis.controller.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.BusStopControllerImpl;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.service.impl.BusStopServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusStopVariables.*;


@WebMvcTest(controllers = {BusStopControllerImpl.class})
public class BusStopControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private BusStopServiceImpl busStopService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusStopControllerTest() throws Exception {
        Mockito.when(busStopService.createBusStop(Mockito.any(BusStopRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/bus_stop")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusStopControllerTest() {
        Mockito.when(busStopService.findByIdBusStop(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(busStopService.findByIdBusStop(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/bus_stop/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/bus_stop/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusStopsControllerTest() throws Exception {
        Mockito.when(busStopService.findAllBusStops()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/bus_stop/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusStopsReturnEmptyControllerTest() throws Exception {
        Mockito.when(busStopService.findAllBusStops()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/bus_stop/list"))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusStopControllerTest() {
        Mockito.when(busStopService.updateBusStop(Mockito.eq(1L), Mockito.any(BusStopRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(busStopService.updateBusStop(Mockito.eq(100L), Mockito.any(BusStopRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/bus_stop/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/bus_stop/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusStopControllerTest() {
        Mockito.when(busStopService.deleteByIdBusStop(1L)).thenReturn(true);
        Mockito.when(busStopService.deleteByIdBusStop(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/bus_stop/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/bus_stop/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusStopsControllerTest() throws Exception {
        Mockito.when(busStopService.deleteAllBusStops()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/bus_stop/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusStopsReturnEmptyControllerTest() throws Exception {
        Mockito.when(busStopService.deleteAllBusStops()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/bus_stop/all").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
