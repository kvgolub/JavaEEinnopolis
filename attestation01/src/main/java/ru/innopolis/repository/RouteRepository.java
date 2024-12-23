package ru.innopolis.repository;

import ru.innopolis.model.Route;

import java.util.List;

public interface RouteRepository {
    List<String> findContainBusStop(String nameStop);
    List<String> findLeastQuantityStation();
    List<String> findLargePassengerFlowAndEffectBus(String nameType);
}
