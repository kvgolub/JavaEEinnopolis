package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.controller.BusController;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.service.BusService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class BusControllerImpl implements BusController {

    private final BusService busService;


    @Override
    public ResponseEntity<BusResponse> createBus(BusRequest busRequest) {
        BusResponse busResponse = busService.createBus(busRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(busResponse);
    }

    @Override
    public ResponseEntity<Optional<BusResponse>> findByIdBus(Long busId) {
        Optional<BusResponse> busResponse = busService.findByIdBus(busId);

        return busResponse.isPresent()
                ? ResponseEntity.ok().body(busResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(busResponse);
    }

    @Override
    public ResponseEntity<List<BusResponse>> findAllBuses() {
        List<BusResponse> busResponseList = busService.findAllBuses();

        return !busResponseList.isEmpty()
                ? ResponseEntity.ok().body(busResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<BusResponse>> updateBus(Long id, BusRequest busRequest) {
        Optional<BusResponse> busResponse = busService.updateBus(id, busRequest);

        if (busResponse.isPresent()) {
            return ResponseEntity.ok().body(busResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(busResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdBus(Long busId) {
        Boolean softDeleteFlag = busService.deleteByIdBus(busId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllBuses() {
        Integer buses = busService.deleteAllBuses();

        if (buses > 0) {
            return ResponseEntity.ok().body(buses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<Boolean> uploadFileBuses(MultipartFile file) throws IOException {
        try {
            Boolean busResponse = busService.uploadFileBuses(file);

            return ResponseEntity.status(HttpStatus.CREATED).body(busResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
