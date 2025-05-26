package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.service.impl.BusStopServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusStopVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class BusStopServiceTest {

    @Autowired
    private BusStopServiceImpl busStopService;


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
    void createBusStop() {
        var response = busStopService.createBusStop(createRequest);

        assertThat("Тестирование \"Создание записи Автобусная остановка\"", response.getNameStop(), equalTo(createResponse.getNameStop()));
    }

    @Order(2)
    @Test
    void findByIdBusStop() {
        var response = busStopService.findByIdBusStop(1L);
        var response2 = busStopService.findByIdBusStop(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Автобусная остановка - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Автобусная остановка - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllBusStops() {
        var response = busStopService.findAllBusStops();

        assertThat("Тестирование \"Поиск всех записей Автобусная остановка - количество\"", response.size(), equalTo(11));
        assertThat("Тестирование \"Поиск всех записей Автобусная остановка - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateBusStop() {
        var response = busStopService.updateBusStop(1L, updateRequest);
        var response2 = busStopService.updateBusStop(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Автобусная остановка - запись есть\"", response.orElseThrow().getNameStop(), equalTo(Optional.of(updateResponse).orElseThrow().getNameStop())),
                () -> assertThat("Тестирование \"Обновление записи Автобусная остановка - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdBusStop() {
        var response = busStopService.deleteByIdBusStop(1L);
        var response2 = busStopService.deleteByIdBusStop(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Автобусная остановка - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Автобусная остановка - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllBusStops() {
        var response = busStopService.deleteAllBusStops();

        assertThat("Тестирование \"Удаление всех записей Автобусная остановка\"", response, equalTo(11));
    }
}
