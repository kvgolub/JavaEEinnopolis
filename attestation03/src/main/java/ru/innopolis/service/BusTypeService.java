package ru.innopolis.service;

import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;

import java.util.List;
import java.util.Optional;


public interface BusTypeService {

    BusTypeResponse createBusType(BusTypeRequest busTypeRequest);
    Optional<BusTypeResponse> findByIdBusType(Long id);
    List<BusTypeResponse> findAllBusTypes();
    Optional<BusTypeResponse> updateBusType(Long id, BusTypeRequest busTypeRequest);
    Boolean deleteByIdBusType(Long id);
    Integer deleteAllBusTypes();

}
