package ru.innopolis.controller.integration;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.ScheduleVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mvc;


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
    @WithMockUser(authorities={"USER"})
    @Test
    void createScheduleControllerTest() throws Exception {
        mvc.perform(post("/api/v1/bus_station/schedule")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(13));
    }

    @Order(2)
    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdScheduleControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/schedule/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/schedule/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @Order(3)
    @WithMockUser(authorities={"USER"})
    @Test
    void findAllSchedulesControllerTest() throws Exception {
        mvc.perform(get("/api/v1/bus_station/schedule/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(13))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Order(4)
    @WithMockUser(authorities={"USER"})
    @Test
    void updateScheduleControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/schedule/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/schedule/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @Order(5)
    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdScheduleControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/schedule/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/schedule/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @Order(6)
    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllSchedulesControllerTest() throws Exception {
        mvc.perform(delete("/api/v1/bus_station/schedule/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(13));
    }
}
