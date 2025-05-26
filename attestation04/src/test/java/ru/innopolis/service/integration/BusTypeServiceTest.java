package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.service.impl.BusTypeServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusTypeVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class BusTypeServiceTest {

    @Autowired
    private BusTypeServiceImpl busTypeService;


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
    void createBusType() {
        var response = busTypeService.createBusType(createRequest);

        assertThat("Тестирование \"Создание записи Тип автобуса\"", response.getNameType(), equalTo(createResponse.getNameType()));
    }

    @Order(2)
    @Test
    void findByIdBusType() {
        var response = busTypeService.findByIdBusType(1L);
        var response2 = busTypeService.findByIdBusType(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Тип автобуса - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Тип автобуса - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllBusTypes() {
        var response = busTypeService.findAllBusTypes();

        assertThat("Тестирование \"Поиск всех записей Тип автобуса - количество\"", response.size(), equalTo(8));
        assertThat("Тестирование \"Поиск всех записей Тип автобуса - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateBusType() {
        var response = busTypeService.updateBusType(1L, updateRequest);
        var response2 = busTypeService.updateBusType(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Тип автобуса - запись есть\"", response.orElseThrow().getNameType(), equalTo(Optional.of(updateResponse).orElseThrow().getNameType())),
                () -> assertThat("Тестирование \"Обновление записи Тип автобуса - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdBusType() {
        var response = busTypeService.deleteByIdBusType(1L);
        var response2 = busTypeService.deleteByIdBusType(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Тип автобуса - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Тип автобуса - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllBusTypes() {
        var response = busTypeService.deleteAllBusTypes();

        assertThat("Тестирование \"Удаление всех записей Тип автобуса\"", response, equalTo(8));
    }
}
