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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class UserControllerTest {

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
    void findByIdUserControllerTest() throws Exception {
        mvc.perform(get("/api/v1/bus_station/user/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user"));
    }

    @Order(2)
    @WithMockUser(authorities={"ADMIN"})
    @Test
    void findAllUsersControllerTest() throws Exception {
        mvc.perform(get("/api/v1/bus_station/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("user"));
    }
}
