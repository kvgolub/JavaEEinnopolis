package ru.innopolis.service;

import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;

import java.util.List;
import java.util.Optional;


public interface BusStopService {

    BusStopResponse createBusStop(BusStopRequest busStopRequest);
    Optional<BusStopResponse> findByIdBusStop(Long id);
    List<BusStopResponse> findAllBusStops();
    Optional<BusStopResponse> updateBusStop(Long id, BusStopRequest busStopRequest);
    Boolean deleteByIdBusStop(Long id);
    Integer deleteAllBusStops();

}
