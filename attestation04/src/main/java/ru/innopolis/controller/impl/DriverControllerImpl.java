package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.controller.DriverController;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.DriverService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class DriverControllerImpl implements DriverController {

    private final DriverService driverService;


    @Override
    public ResponseEntity<DriverResponse> createDriver(DriverRequest driverRequest) {
        DriverResponse driverResponse = driverService.createDriver(driverRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponse);
    }

    @Override
    public ResponseEntity<Optional<DriverResponse>> findByIdDriver(Long driverId) {
        Optional<DriverResponse> driverResponse = driverService.findByIdDriver(driverId);

        return driverResponse.isPresent()
                ? ResponseEntity.ok().body(driverResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(driverResponse);
    }

    @Override
    public ResponseEntity<List<DriverResponse>> findAllDrivers() {
        List<DriverResponse> driverResponseList = driverService.findAllDrivers();

        return !driverResponseList.isEmpty()
                ? ResponseEntity.ok().body(driverResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<DriverResponse>> updateDriver(Long id, DriverRequest driverRequest) {
        Optional<DriverResponse> driverResponse = driverService.updateDriver(id, driverRequest);

        if (driverResponse.isPresent()) {
            return ResponseEntity.ok().body(driverResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(driverResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdDriver(Long driverId) {
        Boolean softDeleteFlag = driverService.deleteByIdDriver(driverId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllDrivers() {
        Integer drivers = driverService.deleteAllDrivers();

        if (drivers > 0) {
            return ResponseEntity.ok().body(drivers);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> uploadFileDrivers(MultipartFile file) throws IOException {
        Boolean busResponse = driverService.uploadFileDrivers(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(busResponse);
    }


    @ExceptionHandler({InvalidFileTypeException.class})
    public void handleException(InvalidFileTypeException e) {
        System.out.println(e.getMessage());
    }
}
