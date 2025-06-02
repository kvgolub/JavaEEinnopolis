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
import ru.innopolis.service.impl.RouteServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.innopolis.utils.BusVariables.uploadFileNull;
import static ru.innopolis.utils.RouteVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class RouteServiceTest {

    @Autowired
    private RouteServiceImpl routeService;


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
    void createRoute() {
        var response = routeService.createRoute(createRequest);

        assertThat("Тестирование \"Создание записи Маршрут\"", response.getNumber(), equalTo(createResponse.getNumber()));
    }

    @Order(2)
    @Test
    void findByIdRoute() {
        var response = routeService.findByIdRoute(1L);
        var response2 = routeService.findByIdRoute(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Маршрут - запись есть\"", response.orElseThrow().getId(), equalTo(Optional.of(findResponse).orElseThrow().getId())),
                () -> assertThat("Тестирование \"Поиск записи Маршрут - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(3)
    @Test
    void findAllRoutes() {
        var response = routeService.findAllRoutes();

        assertThat("Тестирование \"Поиск всех записей Маршрут - количество\"", response.size(), equalTo(11));
        assertThat("Тестирование \"Поиск всех записей Маршрут - первая запись\"", response.get(0).getId(), equalTo(findAllResponse.get(0).getId()));
    }

    @Order(4)
    @Test
    void updateRoute() {
        var response = routeService.updateRoute(1L, updateRequest);
        var response2 = routeService.updateRoute(100L, updateRequest);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Обновление записи Маршрут - запись есть\"", response.orElseThrow().getNumber(), equalTo(Optional.of(updateResponse).orElseThrow().getNumber())),
                () -> assertThat("Тестирование \"Обновление записи Маршрут - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(5)
    @Test
    void deleteByIdRoute() {
        var response = routeService.deleteByIdRoute(1L);
        var response2 = routeService.deleteByIdRoute(100L);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Удаление записи Маршрут - запись есть\"", response, is(true)),
                () -> assertThat("Тестирование \"Удаление записи Маршрут - записи нет\"", response2, is(false))
        );
    }

    @Order(6)
    @Test
    void deleteAllRoutes() {
        var response = routeService.deleteAllRoutes();

        assertThat("Тестирование \"Удаление всех записей Маршрут\"", response, equalTo(11));
    }

    @Order(7)
    @Test
    void uploadFileRoutes() {
        var response = routeService.uploadFileRoutes(uploadFile);

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Загрузка данных для записей Маршрут\"", response, is(true)),
                () -> Assertions.assertThrows(InvalidFileTypeException.class, () -> routeService.uploadFileRoutes(uploadFileNull))
        );
    }
}
