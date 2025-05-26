package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.exception.InvalidFileTypeException;
import ru.innopolis.service.impl.DriverServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.DriverVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class DriverServiceTest {

    @Autowired
    private DriverServiceImpl driverService;


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
    void createDriver() {
        var response = driverService.createDriver(createRequest);

        assertThat("Тестирование \"Создание записи Водитель\"", response.getSurname(), equalTo(createResponse.getSurname()));
    }

    @Order(2)
    @Test
    void findByIdDriver() {
        var response = driverService.findByIdDriver(1L);
        var response2 = driverService.findByIdDriver(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Водитель - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Водитель - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllDrivers() {
        var response = driverService.findAllDrivers();

        assertThat("Тестирование \"Поиск всех записей Водитель - количество\"", response.size(), equalTo(7));
        assertThat("Тестирование \"Поиск всех записей Водитель - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateDriver() {
        var response = driverService.updateDriver(1L, updateRequest);
        var response2 = driverService.updateDriver(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Водитель - запись есть\"", response.orElseThrow().getSurname(), equalTo(Optional.of(updateResponse).orElseThrow().getSurname())),
                () -> assertThat("Тестирование \"Обновление записи Водитель - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdDriver() {
        var response = driverService.deleteByIdDriver(1L);
        var response2 = driverService.deleteByIdDriver(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Водитель - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Водитель - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllDrivers() {
        var response = driverService.deleteAllDrivers();

        assertThat("Тестирование \"Удаление всех записей Водитель\"", response, equalTo(7));
    }

    @Order(7)
    @Test
    void uploadFileDrivers() {
        var response = driverService.uploadFileDrivers(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Водитель\"", response, is(true)),
                () -> Assertions.assertThrows(InvalidFileTypeException.class, () -> driverService.uploadFileDrivers(uploadFileNull))
        );
    }
}
