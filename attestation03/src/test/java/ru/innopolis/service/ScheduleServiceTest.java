package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.entity.Schedule;
import ru.innopolis.repository.ScheduleRepository;
import ru.innopolis.service.impl.ScheduleServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {ScheduleServiceImpl.class})
class ScheduleServiceTest {

    @MockitoBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    ScheduleServiceTest() throws ParseException {
    }


    @Test
    void createSchedule() {
        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(schedule);
        var response = scheduleService.createSchedule(scheduleRequest);

        Assertions.assertEquals(scheduleResponse.getRouteDate(), response.getRouteDate());
    }

    @Test
    void findByIdSchedule() {
        Mockito.when(scheduleRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(schedule));
        var response = scheduleService.findByIdSchedule(1L);

        Assertions.assertEquals(Optional.of(scheduleResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllSchedules() {
        Mockito.when(scheduleRepository.findAll()).thenReturn(schedulesAll);
        var response = scheduleService.findAllSchedules();

        Assertions.assertEquals(scheduleResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateSchedule() {
        Mockito.when(scheduleRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(schedule));
        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(scheduleNew);
        var response = scheduleService.updateSchedule(1L, scheduleRequestNew);

        Assertions.assertEquals(Optional.of(scheduleResponseNew).orElseThrow().getRouteDate(), response.orElseThrow().getRouteDate());
    }

    @Test
    void deleteByIdSchedule() {
        Mockito.when(scheduleRepository.deleteByIdAsSoft(Mockito.any(Long.class))).thenReturn(1);
        var response = scheduleService.deleteByIdSchedule(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllSchedules() {
        Mockito.when(scheduleRepository.deleteAllAsSoft()).thenReturn(1);
        var response = scheduleService.deleteAllSchedules();

        Assertions.assertEquals(1, response);
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final Schedule schedule = new Schedule(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L);
    private final ScheduleRequest scheduleRequest = new ScheduleRequest(sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L);
    private final ScheduleResponse scheduleResponse = new ScheduleResponse(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L);


    // findAll
    private final List<Schedule> schedulesAll = List.of(
            new Schedule(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L),
            new Schedule(2L, sdf.parse("2024-03-02T10:00:00.000+02:00"), 2L, 5L, 1L)
    );
    private final List<ScheduleResponse> scheduleResponseAll = List.of(
            new ScheduleResponse(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L),
            new ScheduleResponse(2L, sdf.parse("2024-03-02T10:00:00.000+02:00"), 2L, 5L, 1L)
    );

    // update
    private final Schedule scheduleNew = new Schedule(1L, sdf.parse("2024-03-03T11:00:00.000+02:00"), 5L, 1L, 6L);
    private final ScheduleRequest scheduleRequestNew = new ScheduleRequest(sdf.parse("2024-03-03T11:00:00.000+02:00"), 5L, 1L, 6L);
    private final ScheduleResponse scheduleResponseNew = new ScheduleResponse(1L, sdf.parse("2024-03-03T11:00:00.000+02:00"), 5L, 1L, 6L);

}