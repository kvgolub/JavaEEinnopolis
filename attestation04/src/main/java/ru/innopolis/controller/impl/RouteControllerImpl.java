package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.controller.RouteController;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.RouteService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class RouteControllerImpl implements RouteController {

    private final RouteService routeService;


    @Override
    public ResponseEntity<RouteResponse> createRoute(RouteRequest routeRequest) {
        RouteResponse routeResponse = routeService.createRoute(routeRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(routeResponse);
    }

    @Override
    public ResponseEntity<Optional<RouteResponse>> findByIdRoute(Long routeId) {
        Optional<RouteResponse> routeResponse = routeService.findByIdRoute(routeId);

        return routeResponse.isPresent()
                ? ResponseEntity.ok().body(routeResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(routeResponse);
    }

    @Override
    public ResponseEntity<List<RouteResponse>> findAllRoutes() {
        List<RouteResponse> routeResponseList = routeService.findAllRoutes();

        return !routeResponseList.isEmpty()
                ? ResponseEntity.ok().body(routeResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<RouteResponse>> updateRoute(Long id, RouteRequest routeRequest) {
        Optional<RouteResponse> routeResponse = routeService.updateRoute(id, routeRequest);

        if (routeResponse.isPresent()) {
            return ResponseEntity.ok().body(routeResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(routeResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdRoute(Long routeId) {
        Boolean softDeleteFlag = routeService.deleteByIdRoute(routeId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllRoutes() {
        Integer routes = routeService.deleteAllRoutes();

        if (routes > 0) {
            return ResponseEntity.ok().body(routes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> uploadFileRoutes(MultipartFile file) throws IOException {
        Boolean routeResponse = routeService.uploadFileRoutes(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(routeResponse);
    }


    @ExceptionHandler({InvalidFileTypeException.class})
    public void handleException(InvalidFileTypeException e) {
        System.out.println(e.getMessage());
    }
}
