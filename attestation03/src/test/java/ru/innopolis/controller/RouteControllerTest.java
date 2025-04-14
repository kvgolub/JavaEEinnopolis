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
import ru.innopolis.controller.impl.RouteControllerImpl;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;
import ru.innopolis.service.impl.RouteServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {RouteControllerImpl.class})
public class RouteControllerTest {

    @MockitoBean
    private RouteServiceImpl routeService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void createRouteControllerTest() throws Exception {
        Mockito.when(routeService.createRoute(Mockito.any(RouteRequest.class))).thenReturn(routeResponse);

        mvc.perform(post("/api/v1/bus_station/route")
                        .contentType("application/json")
                        .content(routeRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdRouteControllerTest() throws Exception {
        Mockito.when(routeService.findByIdRoute(Mockito.any(Long.class))).thenReturn(Optional.of(routeResponse));

        mvc.perform(get("/api/v1/bus_station/route/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(routeResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllRoutesControllerTest() throws Exception {
        Mockito.when(routeService.findAllRoutes()).thenReturn(routeResponseAll);

        mvc.perform(get("/api/v1/bus_station/route/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateRouteControllerTest() throws Exception {
        Mockito.when(routeService.updateRoute(Mockito.any(Long.class), Mockito.any(RouteRequest.class))).thenReturn(Optional.of(routeResponseNew));

        mvc.perform(put("/api/v1/bus_station/route/1")
                        .contentType("application/json")
                        .content(routeRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(routeResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdRouteControllerTest() throws Exception {
        Mockito.when(routeService.deleteByIdRoute(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/route/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllRoutesControllerTest() throws Exception {
        Mockito.when(routeService.deleteAllRoutes()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/route/all").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileRoutesControllerTest() throws Exception {
        Mockito.when(routeService.uploadFileRoutes(Mockito.any(MultipartFile.class))).thenReturn(true);
        mvc.perform(multipart("/api/v1/bus_station/route/file")
                .file(routeFile)
                .with(csrf())
                )
                .andExpect(status().isCreated());
    }


    // create
    private final RouteResponse routeResponse = new RouteResponse(1L, "1А", 1L, 5L,10,500);
    private final String routeRequestBody = """
                            {
                            	"number": "1А",
                             	"startStation": 1,
                             	"endStation": 5,
                             	"quantityStation": 10,
                             	"passengerFlow": 500
                            }
                        """;

    // find
    private final String routeResponseJson = """
                            {
                            	"id": 1,
                            	"number": "1А",
                             	"startStation": 1,
                             	"endStation": 5,
                             	"quantityStation": 10,
                             	"passengerFlow": 500
                            }
                        """;

    // findAll
    private final List<RouteResponse> routeResponseAll = List.of(
            new RouteResponse(1L, "1А", 1L, 5L,10,500),
            new RouteResponse(2L, "15", 2L, 6L,20,1000)
    );

    // update
    private final RouteResponse routeResponseNew = new RouteResponse(1L, "2", 3L, 4L,15,200);
    private final String routeRequestNewBody = """
                            {
                                "number": "2",
                             	"startStation": 3,
                             	"endStation": 4,
                             	"quantityStation": 15,
                             	"passengerFlow": 200
                            }
                        """;
    private final String routeResponseNewJson = """
                            {
                                "id": 1,
                                "number": "2",
                             	"startStation": 3,
                             	"endStation": 4,
                             	"quantityStation": 15,
                             	"passengerFlow": 200
                            }
                        """;

    // uploadFile
    private final MockMultipartFile routeFile = new MockMultipartFile(
            "file",
            "routesRequest.json",
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
