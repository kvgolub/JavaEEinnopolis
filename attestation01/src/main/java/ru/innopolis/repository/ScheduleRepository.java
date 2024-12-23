package ru.innopolis.repository;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository {
    List<String> findAllScheduleDetail();
    void updateBusOnDate(String model, String surname, String routeDate);
    void deleteBusFromSchedule(String model);
}
