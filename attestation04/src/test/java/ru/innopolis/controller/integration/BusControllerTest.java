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

import static ru.innopolis.utils.BusVariables.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class BusControllerTest {

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
    void createBusControllerTest() throws Exception {
        mvc.perform(post("/api/v1/bus_station/bus")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11));
    }

    @Order(2)
    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdBusControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/bus/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/bus/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @Order(3)
    @WithMockUser(authorities={"USER"})
    @Test
    void findAllBusesControllerTest() throws Exception {
        mvc.perform(get("/api/v1/bus_station/bus/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(11))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Order(4)
    @WithMockUser(authorities={"USER"})
    @Test
    void updateBusControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/bus/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/bus/100")
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
    void deleteByIdBusControllerTest() {
        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/bus/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/bus/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @Order(6)
    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllBusesControllerTest() throws Exception {
        mvc.perform(delete("/api/v1/bus_station/bus/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @Order(7)
    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileBusesControllerTest() throws Exception {
        mvc.perform(multipart("/api/v1/bus_station/bus/file")
                .file(uploadFile)
                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(true));
    }

    @Order(8)
    @WithMockUser(authorities={"USER"})
    @Test
    void uploadFileBusesReturnIncorrectControllerTest() throws Exception {
        mvc.perform(multipart("/api/v1/bus_station/bus/file")
                        .file(uploadFileNull)
                        .with(csrf())
                )
                .andExpect(result -> Assertions.assertInstanceOf(RuntimeException.class, result.getResolvedException()));
    }
}
