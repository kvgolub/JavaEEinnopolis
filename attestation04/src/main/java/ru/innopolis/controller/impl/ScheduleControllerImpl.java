package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.ScheduleController;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.service.ScheduleService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;


    @Override
    public ResponseEntity<ScheduleResponse> createSchedule(ScheduleRequest scheduleRequest) {
        ScheduleResponse scheduleResponse = scheduleService.createSchedule(scheduleRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    @Override
    public ResponseEntity<Optional<ScheduleResponse>> findByIdSchedule(Long scheduleId) {
        Optional<ScheduleResponse> scheduleResponse = scheduleService.findByIdSchedule(scheduleId);

        return scheduleResponse.isPresent()
                ? ResponseEntity.ok().body(scheduleResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(scheduleResponse);
    }

    @Override
    public ResponseEntity<List<ScheduleResponse>> findAllSchedules() {
        List<ScheduleResponse> scheduleResponseList = scheduleService.findAllSchedules();

        return !scheduleResponseList.isEmpty()
                ? ResponseEntity.ok().body(scheduleResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<ScheduleResponse>> updateSchedule(Long id, ScheduleRequest scheduleRequest) {
        Optional<ScheduleResponse> scheduleResponse = scheduleService.updateSchedule(id, scheduleRequest);

        if (scheduleResponse.isPresent()) {
            return ResponseEntity.ok().body(scheduleResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(scheduleResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdSchedule(Long scheduleId) {
        Boolean softDeleteFlag = scheduleService.deleteByIdSchedule(scheduleId);

        if (softDeleteFlag) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllSchedules() {
        Integer schedules = scheduleService.deleteAllSchedules();

        if (schedules > 0) {
            return ResponseEntity.ok().body(schedules);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
