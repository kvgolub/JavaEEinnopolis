package ru.innopolis.service;

import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;

import java.util.List;
import java.util.Optional;


public interface ScheduleService {

    ScheduleResponse createSchedule(ScheduleRequest scheduleRequest);
    Optional<ScheduleResponse> findByIdSchedule(Long id);
    List<ScheduleResponse> findAllSchedules();
    Optional<ScheduleResponse> updateSchedule(Long id, ScheduleRequest scheduleRequest);
    Boolean deleteByIdSchedule(Long id);
    Integer deleteAllSchedules();

}
