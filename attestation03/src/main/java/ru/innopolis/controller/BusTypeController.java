package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/bus_type")
public interface BusTypeController {

    @PostMapping
    ResponseEntity<BusTypeResponse> createBusType(@Valid @RequestBody BusTypeRequest busTypeRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<BusTypeResponse>> findByIdBusType(@Valid @PathVariable("id") Long busTypeId);

    @GetMapping("/list")
    ResponseEntity<List<BusTypeResponse>> findAllBusTypes();

    @PutMapping("/{id}")
    ResponseEntity<Optional<BusTypeResponse>> updateBusType(@Valid @PathVariable Long id, @Valid @RequestBody BusTypeRequest busTypeRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBusType(@Valid @PathVariable("id") Long busTypeId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBusTypes();

}
