package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/driver")
public interface DriverController {

    @PostMapping
    ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest driverRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<DriverResponse>> findByIdDriver(@Valid @PathVariable("id") Long driverId);

    @GetMapping("/list")
    ResponseEntity<List<DriverResponse>> findAllDrivers();

    @PutMapping("/{id}")
    ResponseEntity<Optional<DriverResponse>> updateDriver(@Valid @PathVariable Long id, @Valid @RequestBody DriverRequest driverRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdDriver(@Valid @PathVariable("id") Long driverId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllDrivers();


    @PostMapping(value = "/file")
    ResponseEntity<Boolean> uploadFileDrivers(@RequestPart MultipartFile file) throws IOException;

}
