package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.BusTypeController;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;
import ru.innopolis.service.BusTypeService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class BusTypeControllerImpl implements BusTypeController {

    private final BusTypeService busTypeService;


    @Override
    public ResponseEntity<BusTypeResponse> createBusType(BusTypeRequest busTypeRequest) {
        BusTypeResponse busTypeResponse = busTypeService.createBusType(busTypeRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(busTypeResponse);
    }

    @Override
    public ResponseEntity<Optional<BusTypeResponse>> findByIdBusType(Long busTypeId) {
        Optional<BusTypeResponse> busTypeResponse = busTypeService.findByIdBusType(busTypeId);

        return busTypeResponse.isPresent()
                ? ResponseEntity.ok().body(busTypeResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(busTypeResponse);
    }

    @Override
    public ResponseEntity<List<BusTypeResponse>> findAllBusTypes() {
        List<BusTypeResponse> busTypeResponseList = busTypeService.findAllBusTypes();

        return !busTypeResponseList.isEmpty()
                ? ResponseEntity.ok().body(busTypeResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<BusTypeResponse>> updateBusType(Long id, BusTypeRequest busTypeRequest) {
        Optional<BusTypeResponse> busTypeResponse = busTypeService.updateBusType(id, busTypeRequest);

        if (busTypeResponse.isPresent()) {
            return ResponseEntity.ok().body(busTypeResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(busTypeResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdBusType(Long busTypeId) {
        Boolean softDeleteFlag = busTypeService.deleteByIdBusType(busTypeId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllBusTypes() {
        Integer types = busTypeService.deleteAllBusTypes();

        if (types > 0) {
            return ResponseEntity.ok().body(types);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
