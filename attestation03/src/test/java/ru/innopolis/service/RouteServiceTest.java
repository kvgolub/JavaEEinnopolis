package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;
import ru.innopolis.entity.Route;
import ru.innopolis.repository.RouteRepository;
import ru.innopolis.service.impl.RouteServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {RouteServiceImpl.class})
class RouteServiceTest {

    @MockitoBean
    private RouteRepository routeRepository;

    @Autowired
    private RouteServiceImpl routeService;


    @Test
    void createRoute() {
        Mockito.when(routeRepository.save(Mockito.any(Route.class))).thenReturn(route);
        var response = routeService.createRoute(routeRequest);

        Assertions.assertEquals(routeResponse.getNumber(), response.getNumber());
    }

    @Test
    void findByIdRoute() {
        Mockito.when(routeRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(route));
        var response = routeService.findByIdRoute(1L);

        Assertions.assertEquals(Optional.of(routeResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllRoutes() {
        Mockito.when(routeRepository.findAll()).thenReturn(routesAll);
        var response = routeService.findAllRoutes();

        Assertions.assertEquals(routeResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateRoute() {
        Mockito.when(routeRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(route));
        Mockito.when(routeRepository.save(Mockito.any(Route.class))).thenReturn(routeNew);
        var response = routeService.updateRoute(1L, routeRequestNew);

        Assertions.assertEquals(Optional.of(routeResponseNew).orElseThrow().getNumber(), response.orElseThrow().getNumber());
    }

    @Test
    void deleteByIdRoute() {
        Mockito.when(routeRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = routeService.deleteByIdRoute(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllRoutes() {
        Mockito.when(routeRepository.deleteAllAsSoft()).thenReturn(1);
        var response = routeService.deleteAllRoutes();

        Assertions.assertEquals(1, response);
    }

    @Test
    void uploadFileRoutes() throws IOException {
        var response = routeService.uploadFileRoutes(routeFile);

        Assertions.assertEquals(true, response);
    }


    // create
    private final Route route = new Route(1L, "1А", 1L, 5L,10,500);
    private final RouteRequest routeRequest = new RouteRequest("1А", 1L, 5L,10,500);
    private final RouteResponse routeResponse = new RouteResponse(1L, "1А", 1L, 5L,10,500);


    // findAll
    private final List<Route> routesAll = List.of(
            new Route(1L, "1А", 1L, 5L,10,500),
            new Route(2L, "15", 2L, 6L,20,1000)
    );
    private final List<RouteResponse> routeResponseAll = List.of(
            new RouteResponse(1L, "1А", 1L, 5L,10,500),
            new RouteResponse(2L, "15", 2L, 6L,20,1000)
    );

    // update
    private final Route routeNew = new Route(1L, "2", 3L, 4L,15,200);
    private final RouteRequest routeRequestNew = new RouteRequest("2", 3L, 4L,15,200);
    private final RouteResponse routeResponseNew = new RouteResponse(1L, "2", 3L, 4L,15,200);

    // uploadFile
    private final MockMultipartFile routeFile = new MockMultipartFile(
            "file",
            "routesRequest.json",
            "application/json",
            """
                [
                    {
                        "number": "2000Э",
                        "startStation": 2,
                        "endStation": 8,
                        "quantityStation": 22,
                        "passengerFlow": 18050
                    }
                ]
            """.getBytes()
    );

}