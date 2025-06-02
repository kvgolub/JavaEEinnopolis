package ru.innopolis.controller.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.BusTypeControllerImpl;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.service.impl.BusTypeServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusTypeVariables.*;


@WebMvcTest(controllers = {BusTypeControllerImpl.class})
public class BusTypeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private BusTypeServiceImpl busTypeService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusTypeControllerTest() throws Exception {
        Mockito.when(busTypeService.createBusType(Mockito.any(BusTypeRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/bus_type")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(8));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusTypeControllerTest() {
        Mockito.when(busTypeService.findByIdBusType(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(busTypeService.findByIdBusType(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/bus_type/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/bus_type/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusTypesControllerTest() throws Exception {
        Mockito.when(busTypeService.findAllBusTypes()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/bus_type/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusTypesReturnEmptyControllerTest() throws Exception {
        Mockito.when(busTypeService.findAllBusTypes()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/bus_type/list"))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusTypeControllerTest() {
        Mockito.when(busTypeService.updateBusType(Mockito.eq(1L), Mockito.any(BusTypeRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(busTypeService.updateBusType(Mockito.eq(100L), Mockito.any(BusTypeRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/bus_type/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/bus_type/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusTypeControllerTest() {
        Mockito.when(busTypeService.deleteByIdBusType(1L)).thenReturn(true);
        Mockito.when(busTypeService.deleteByIdBusType(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/bus_type/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/bus_type/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusTypesControllerTest() throws Exception {
        Mockito.when(busTypeService.deleteAllBusTypes()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/bus_type/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusTypesReturnEmptyControllerTest() throws Exception {
        Mockito.when(busTypeService.deleteAllBusTypes()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/bus_type/all").with(csrf()))
                .andExpect(status().isNoContent());
    }

}
