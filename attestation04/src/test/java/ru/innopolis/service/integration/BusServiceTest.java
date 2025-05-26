package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.service.impl.BusServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class BusServiceTest {

    @Autowired
    private BusServiceImpl busService;


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
    void createBus() {
        var response = busService.createBus(createRequest);

        assertThat("Тестирование \"Создание записи Автобус\"", response.getModel(), equalTo(createResponse.getModel()));
    }

    @Order(2)
    @Test
    void findByIdBus() {
        var response = busService.findByIdBus(1L);
        var response2 = busService.findByIdBus(100L);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
            () -> assertThat("Тестирование \"Поиск записи Автобус - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
            () -> assertThat("Тестирование \"Поиск записи Автобус - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllBuses() {
        var response = busService.findAllBuses();

        assertThat("Тестирование \"Поиск всех записей Автобус - количество\"", response.size(), equalTo(11));
        assertThat("Тестирование \"Поиск всех записей Автобус - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateBus() {
        var response = busService.updateBus(1L, updateRequest);
        var response2 = busService.updateBus(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> assertThat("Тестирование \"Обновление записи Автобус - запись есть\"", response.orElseThrow().getModel(), equalTo(Optional.of(updateResponse).orElseThrow().getModel())),
                () -> assertThat("Тестирование \"Обновление записи Автобус - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdBus() {
        var response = busService.deleteByIdBus(1L);
        var response2 = busService.deleteByIdBus(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Автобус - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Автобус - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllBuses() {
        var response = busService.deleteAllBuses();

        assertThat("Тестирование \"Удаление всех записей Автобус\"", response, equalTo(11));
    }

    @Order(7)
    @Test
    void uploadFileBuses() {
        var response = busService.uploadFileBuses(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Автобус\"", response, is(true)),
                () -> Assertions.assertThrows(Exception.class, () -> busService.uploadFileBuses(null))
        );
    }
}
