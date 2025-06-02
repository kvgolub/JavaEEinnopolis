package ru.innopolis.service.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static ru.innopolis.utils.UserVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;


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
    void findByIdUser() {
        var response = userService.findByIdUser("user");
        var response2 = userService.findByIdUser("viewer");

        Assertions.assertAll("Soft assertion проверка для исключений",
                () -> assertThat("Тестирование \"Поиск записи Пользователь - запись есть\"", response.orElseThrow().getUsername(), equalTo(Optional.of(findResponse).orElseThrow().getUsername())),
                () -> assertThat("Тестирование \"Поиск записи Пользователь - записи нет\"", response2, equalTo(Optional.empty()))
        );
    }

    @Order(2)
    @Test
    void findAllUsers() {
        var response = userService.findAllUsers();

        assertThat("Тестирование \"Поиск всех записей Пользователь - количество\"", response.size(), equalTo(2));
        assertThat("Тестирование \"Поиск всех записей Пользователь - первая запись\"", response.get(0).getUsername(), equalTo(findAllResponse.get(0).getUsername()));
    }
}
