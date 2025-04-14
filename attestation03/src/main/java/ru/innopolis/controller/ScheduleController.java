package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/bus_station/schedule")
public interface ScheduleController {

    @PostMapping
    ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<ScheduleResponse>> findByIdSchedule(@Valid @PathVariable("id") Long scheduleId);

    @GetMapping("/list")
    ResponseEntity<List<ScheduleResponse>> findAllSchedules();

    @PutMapping("/{id}")
    ResponseEntity<Optional<ScheduleResponse>> updateSchedule(@Valid @PathVariable Long id, @Valid @RequestBody ScheduleRequest scheduleRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdSchedule(@Valid @PathVariable("id") Long scheduleId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllSchedules();

}
