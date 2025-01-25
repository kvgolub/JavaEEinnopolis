package ru.innopolis.repository;

import ru.innopolis.model.Route;
import java.util.List;


public interface RouteRepository {
    void create(String number, Integer startStation, Integer endStation, Integer quantityStation, Integer passengerFlow);
    void createWithId(Integer id, String number, Integer startStation, Integer endStation, Integer quantityStation, Integer passengerFlow);
    List<Route> findById(Integer id);
    List<Route> findAll();
    Integer update(Object id, Object number, Object startStation, Object endStation, Object quantityStation, Object passengerFlow);
    Integer deleteByID(Integer id);
    void deleteAll();

    List<String> findContainBusStop(String nameStop);
    List<String> findLeastQuantityStation();
    List<String> findLargePassengerFlowAndEffectBus(String nameType);
}
