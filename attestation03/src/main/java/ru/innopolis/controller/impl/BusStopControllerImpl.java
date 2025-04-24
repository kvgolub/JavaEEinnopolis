package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.BusStopController;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;
import ru.innopolis.service.BusStopService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class BusStopControllerImpl implements BusStopController {

    private final BusStopService busStopService;


    @Override
    public ResponseEntity<BusStopResponse> createBusStop(BusStopRequest busStopRequest) {
        BusStopResponse busStopResponse = busStopService.createBusStop(busStopRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(busStopResponse);
    }

    @Override
    public ResponseEntity<Optional<BusStopResponse>> findByIdBusStop(Long busStopId) {
        Optional<BusStopResponse> busStopResponse = busStopService.findByIdBusStop(busStopId);

        return busStopResponse.isPresent()
                ? ResponseEntity.ok().body(busStopResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(busStopResponse);
    }

    @Override
    public ResponseEntity<List<BusStopResponse>> findAllBusStops() {
        List<BusStopResponse> busStopResponseList = busStopService.findAllBusStops();

        return !busStopResponseList.isEmpty()
                ? ResponseEntity.ok().body(busStopResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<BusStopResponse>> updateBusStop(Long id, BusStopRequest busStopRequest) {
        Optional<BusStopResponse> busStopResponse = busStopService.updateBusStop(id, busStopRequest);

        if (busStopResponse.isPresent()) {
            return ResponseEntity.ok().body(busStopResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(busStopResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdBusStop(Long busStopId) {
        Boolean softDeleteFlag = busStopService.deleteByIdBusStop(busStopId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllBusStops() {
        Integer busStops = busStopService.deleteAllBusStops();

        if (busStops > 0) {
            return ResponseEntity.ok().body(busStops);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
