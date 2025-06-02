package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.service.impl.ScheduleServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.ScheduleVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class ScheduleServiceTest {

    @Autowired
    private ScheduleServiceImpl scheduleService;


    @Container
    public static PostgreSQLContainer<?> database  = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("bus_station")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.flyway.url", database::getJdbcUrl);
    }


    @Order(1)
    @Test
    void createSchedule() {
        var response = scheduleService.createSchedule(createRequest);

        assertThat("Тестирование \"Создание записи Расписание\"", response.getRouteDate(), equalTo(createResponse.getRouteDate()));
    }

    @Order(2)
    @Test
    void findByIdSchedule() {
        var response = scheduleService.findByIdSchedule(1L);
        var response2 = scheduleService.findByIdSchedule(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Расписание - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Расписание - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllSchedules() {
        var response = scheduleService.findAllSchedules();

        assertThat("Тестирование \"Поиск всех записей Расписание - количество\"", response.size(), equalTo(13));
        assertThat("Тестирование \"Поиск всех записей Расписание - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateSchedule() {
        var response = scheduleService.updateSchedule(1L, updateRequest);
        var response2 = scheduleService.updateSchedule(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Расписание - запись есть\"", response.orElseThrow().getRouteDate(), equalTo(Optional.of(updateResponse).orElseThrow().getRouteDate())),
                () -> assertThat("Тестирование \"Обновление записи Расписание - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdSchedule() {
        var response = scheduleService.deleteByIdSchedule(1L);
        var response2 = scheduleService.deleteByIdSchedule(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Расписание - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Расписание - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllSchedules() {
        var response = scheduleService.deleteAllSchedules();

        assertThat("Тестирование \"Удаление всех записей Расписание\"", response, equalTo(13));
    }
}
