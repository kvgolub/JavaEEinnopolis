package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/route")
public interface RouteController {

    @PostMapping
    ResponseEntity<RouteResponse> createRoute(@Valid @RequestBody RouteRequest routeRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<RouteResponse>> findByIdRoute(@Valid @PathVariable("id") Long routeId);

    @GetMapping("/list")
    ResponseEntity<List<RouteResponse>> findAllRoutes();

    @PutMapping("/{id}")
    ResponseEntity<Optional<RouteResponse>> updateRoute(@Valid @PathVariable Long id, @Valid @RequestBody RouteRequest routeRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdRoute(@Valid @PathVariable("id") Long routeId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllRoutes();


    @PostMapping(value = "/file")
    ResponseEntity<Boolean> uploadFileRoutes(@RequestPart MultipartFile file) throws IOException;

}
