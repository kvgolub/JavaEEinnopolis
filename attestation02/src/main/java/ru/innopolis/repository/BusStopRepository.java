package ru.innopolis.repository;

import ru.innopolis.model.BusStop;

import java.util.List;


public interface BusStopRepository {
    void create(String nameStop);
    void createWithId(Integer id, String nameStop);
    List<BusStop> findById(Integer id);
    List<BusStop> findAll();
    Integer update(Object id, Object nameStop);
    Integer deleteByID(Integer id);
    void deleteAll();

    List<Integer> findQuantityBusStop();
}
