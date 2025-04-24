package ru.innopolis.service;

import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface RouteService {

    RouteResponse createRoute(RouteRequest routeRequest);
    Optional<RouteResponse> findByIdRoute(Long id);
    List<RouteResponse> findAllRoutes();
    Optional<RouteResponse> updateRoute(Long id, RouteRequest routeRequest);
    Boolean deleteByIdRoute(Long id);
    Integer deleteAllRoutes();

    Boolean uploadFileRoutes (MultipartFile file) throws IOException;

}
