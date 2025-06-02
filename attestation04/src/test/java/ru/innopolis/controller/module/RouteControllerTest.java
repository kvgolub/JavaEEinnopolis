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
import ru.innopolis.controller.impl.RouteControllerImpl;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.impl.RouteServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusVariables.updateRequestBody;
import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.RouteVariables.*;


@WebMvcTest(controllers = {RouteControllerImpl.class})
public class RouteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private RouteServiceImpl routeService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createRouteControllerTest() throws Exception {
        Mockito.when(routeService.createRoute(Mockito.any(RouteRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/route")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdRouteControllerTest() {
        Mockito.when(routeService.findByIdRoute(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(routeService.findByIdRoute(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/route/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/route/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllRoutesControllerTest() throws Exception {
        Mockito.when(routeService.findAllRoutes()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/route/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllRoutesReturnEmptyControllerTest() throws Exception {
        Mockito.when(routeService.findAllRoutes()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/route/list"))
                .andExpect(status().isNoContent());
    }


    @WithMockUser(authorities={"USER"})
    @Test
    void updateRouteControllerTest() {
        Mockito.when(routeService.updateRoute(Mockito.eq(1L), Mockito.any(RouteRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(routeService.updateRoute(Mockito.eq(100L), Mockito.any(RouteRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/route/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/route/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdRouteControllerTest() {
        Mockito.when(routeService.deleteByIdRoute(1L)).thenReturn(true);
        Mockito.when(routeService.deleteByIdRoute(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/route/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/route/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllRoutesControllerTest() throws Exception {
        Mockito.when(routeService.deleteAllRoutes()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/route/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllRoutesReturnEmptyControllerTest() throws Exception {
        Mockito.when(routeService.deleteAllRoutes()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/route/all").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileRoutesControllerTest() throws Exception {
        Mockito.when(routeService.uploadFileRoutes(Mockito.any(MultipartFile.class))).thenReturn(true);

        mvc.perform(multipart("/api/v1/bus_station/route/file")
                        .file(uploadFile)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(true));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileRoutesReturnIncorrectControllerTest() throws Exception {
        Mockito.when(routeService.uploadFileRoutes(Mockito.any(MultipartFile.class))).thenThrow(InvalidFileTypeException.class);

        mvc.perform(multipart("/api/v1/bus_station/route/file")
                        .file(uploadFileNull)
                        .with(csrf())
                )
                .andExpect(result -> Assertions.assertInstanceOf(InvalidFileTypeException.class, result.getResolvedException()));
    }
}
