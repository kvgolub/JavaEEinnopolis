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
import ru.innopolis.controller.impl.BusControllerImpl;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.impl.BusServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusVariables.*;


@WebMvcTest(controllers = {BusControllerImpl.class})
public class BusControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private BusServiceImpl busService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createBusControllerTest() throws Exception {
        Mockito.when(busService.createBus(Mockito.any(BusRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/bus")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusControllerTest() {
        Mockito.when(busService.findByIdBus(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(busService.findByIdBus(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/bus/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/bus/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusesControllerTest() throws Exception {
        Mockito.when(busService.findAllBuses()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/bus/list"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.size()").value(2))
                        .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusesReturnEmptyControllerTest() throws Exception {
        Mockito.when(busService.findAllBuses()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/bus/list"))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusControllerTest() {
        Mockito.when(busService.updateBus(Mockito.eq(1L), Mockito.any(BusRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(busService.updateBus(Mockito.eq(100L), Mockito.any(BusRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/bus/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/bus/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdBusControllerTest() {
        Mockito.when(busService.deleteByIdBus(1L)).thenReturn(true);
        Mockito.when(busService.deleteByIdBus(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/bus/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/bus/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusesControllerTest() throws Exception {
        Mockito.when(busService.deleteAllBuses()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/bus/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusesReturnEmptyControllerTest() throws Exception {
        Mockito.when(busService.deleteAllBuses()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/bus/all").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileBusesControllerTest() throws Exception {
        Mockito.when(busService.uploadFileBuses(Mockito.any(MultipartFile.class))).thenReturn(true);

        mvc.perform(multipart("/api/v1/bus_station/bus/file")
                        .file(uploadFile)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(true));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileBusesReturnIncorrectControllerTest() throws Exception {
        Mockito.when(busService.uploadFileBuses(Mockito.any(MultipartFile.class))).thenThrow(InvalidFileTypeException.class);

        mvc.perform(multipart("/api/v1/bus_station/bus/file")
                        .file(uploadFileNull)
                        .with(csrf())
                )
                .andExpect(result -> Assertions.assertInstanceOf(InvalidFileTypeException.class, result.getResolvedException()));
    }
}
