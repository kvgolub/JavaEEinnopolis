package ru.innopolis.repository.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.innopolis.repository.RouteRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@DataJpaTest
@Testcontainers
class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;


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


    @Test
    void deleteByIdAsSoft() {
        var response = routeRepository.deleteByIdAsSoft(1L);

        assertThat("Тестирование \"Логическое удаление записи Маршрут\"", response, equalTo(1));
    }

    @Test
    void deleteAllAsSoft() {
        var response = routeRepository.deleteAllAsSoft();

        assertThat("Тестирование \"Логическое удаление всех записей Маршрут\"", response, equalTo(10));
    }
}
