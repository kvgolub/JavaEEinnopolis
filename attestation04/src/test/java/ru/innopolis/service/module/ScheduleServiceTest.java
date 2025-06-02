package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.entity.Schedule;
import ru.innopolis.repository.ScheduleRepository;
import ru.innopolis.service.impl.ScheduleServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.ScheduleVariables.*;


@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;


    @Test
    void createSchedule() {
        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(create);
        var response = scheduleService.createSchedule(createRequest);

        assertThat("Тестирование \"Создание записи Расписание\"", response.getRouteDate(), equalTo(createResponse.getRouteDate()));
    }

    @Test
    void findByIdSchedule() {
        Mockito.when(scheduleRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(scheduleRepository.findById(100L)).thenReturn(Optional.empty());
        var response = scheduleService.findByIdSchedule(1L);
        var response2 = scheduleService.findByIdSchedule(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Расписание - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Расписание - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void findAllSchedules() {
        Mockito.when(scheduleRepository.findAll()).thenReturn(findAll);
        var response = scheduleService.findAllSchedules();

        assertThat("Тестирование \"Поиск всех записей Расписание - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Расписание - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Test
    void updateSchedule() {
        Mockito.when(scheduleRepository.findById(1L)).thenReturn(Optional.of(find));
        Mockito.when(scheduleRepository.findById(100L)).thenReturn(Optional.empty());
        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(update);
        var response = scheduleService.updateSchedule(1L, updateRequest);
        var response2 = scheduleService.updateSchedule(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Расписание - запись есть\"", response.orElseThrow().getRouteDate(), equalTo(Optional.of(updateResponse).orElseThrow().getRouteDate())),
                () -> assertThat("Тестирование \"Обновление записи Расписание - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Test
    void deleteByIdSchedule() {
        Mockito.when(scheduleRepository.deleteByIdAsSoft(1L)).thenReturn(1);
        Mockito.when(scheduleRepository.deleteByIdAsSoft(100L)).thenReturn(0);
        var response = scheduleService.deleteByIdSchedule(1L);
        var response2 = scheduleService.deleteByIdSchedule(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Расписание - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Расписание - записи нет\"", response2, is(false))
        );
    }

    @Test
    void deleteAllSchedules() {
        Mockito.when(scheduleRepository.deleteAllAsSoft()).thenReturn(11);
        var response = scheduleService.deleteAllSchedules();

        assertThat("Тестирование \"Удаление всех записей Расписание\"", response, equalTo(11));
    }
}
