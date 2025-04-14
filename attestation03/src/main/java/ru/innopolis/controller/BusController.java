package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/bus")
public interface BusController {

    @PostMapping
    ResponseEntity<BusResponse> createBus(@Valid @RequestBody BusRequest busRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<BusResponse>> findByIdBus(@Valid @PathVariable("id") Long busId);

    @GetMapping("/list")
    ResponseEntity<List<BusResponse>> findAllBuses();

    @PutMapping("/{id}")
    ResponseEntity<Optional<BusResponse>> updateBus(@Valid @PathVariable Long id, @Valid @RequestBody BusRequest busRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBus(@Valid @PathVariable("id") Long busId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBuses();


    @PostMapping(value = "/file")
    ResponseEntity<Boolean> uploadFileBuses(@RequestPart MultipartFile file) throws IOException;


}
