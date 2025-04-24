package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/bus_stop")
public interface BusStopController {

    @PostMapping
    ResponseEntity<BusStopResponse> createBusStop(@Valid @RequestBody BusStopRequest busStopRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<BusStopResponse>> findByIdBusStop(@Valid @PathVariable("id") Long busStopId);

    @GetMapping("/list")
    ResponseEntity<List<BusStopResponse>> findAllBusStops();

    @PutMapping("/{id}")
    ResponseEntity<Optional<BusStopResponse>> updateBusStop(@Valid @PathVariable Long id, @Valid @RequestBody BusStopRequest busStopRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBusStop(@Valid @PathVariable("id") Long busStopId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBusStops();

}
