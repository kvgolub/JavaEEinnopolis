package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.entity.Schedule;
import ru.innopolis.repository.ScheduleRepository;
import ru.innopolis.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponse createSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.save(
                new Schedule(
                        null,
                        scheduleRequest.getRouteDate(),
                        scheduleRequest.getRouteId(),
                        scheduleRequest.getBusId(),
                        scheduleRequest.getDriverId()
                )
        );

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getRouteDate(),
                schedule.getRouteId(),
                schedule.getBusId(),
                schedule.getDriverId()
        );
    }

    @Override
    public Optional<ScheduleResponse> findByIdSchedule(Long id) {
        try {
            Schedule schedule = scheduleRepository.findById(id).orElseThrow();

            return Optional.of(
                    new ScheduleResponse(
                            schedule.getId(),
                            schedule.getRouteDate(),
                            schedule.getRouteId(),
                            schedule.getBusId(),
                            schedule.getDriverId()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<ScheduleResponse> findAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleResponse> scheduleResponseList = new ArrayList<>();
        scheduleList.forEach(schedule -> scheduleResponseList.add(
                new ScheduleResponse(
                        schedule.getId(),
                        schedule.getRouteDate(),
                        schedule.getRouteId(),
                        schedule.getBusId(),
                        schedule.getDriverId()
                )
        ));

        return scheduleResponseList;
    }

    @Override
    public Optional<ScheduleResponse> updateSchedule(Long id, ScheduleRequest scheduleRequest) {
        try {
            scheduleRepository.findById(id).orElseThrow();

            Schedule schedule = scheduleRepository.save(
                    new Schedule(
                            id,
                            scheduleRequest.getRouteDate(),
                            scheduleRequest.getRouteId(),
                            scheduleRequest.getBusId(),
                            scheduleRequest.getDriverId()
                    )
            );

            return Optional.of(
                    new ScheduleResponse(
                            schedule.getId(),
                            schedule.getRouteDate(),
                            schedule.getRouteId(),
                            schedule.getBusId(),
                            schedule.getDriverId()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdSchedule(Long id) {
        Integer result = scheduleRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllSchedules() {
        return scheduleRepository.deleteAllAsSoft();
    }
}
