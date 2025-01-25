package ru.innopolis.repository;

import ru.innopolis.model.Route;
import ru.innopolis.model.Schedule;

import java.util.Date;
import java.util.List;


public interface ScheduleRepository {
    void create(String routeDate, Integer route_id, Integer bus_id, Integer driver_id);
    void createWithId(Integer id, String routeDate, Integer route_id, Integer bus_id, Integer driver_id);
    List<Schedule> findById(Integer id);
    List<Schedule> findAll();
    Integer update(Object id, Object routeDate, Object route_id, Object bus_id, Object driver_id);
    Integer deleteByID(Integer id);
    void deleteAll();

    List<String> findAllScheduleDetail();
}
