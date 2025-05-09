package ru.innopolis.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.dto.task.TaskResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-postgres.properties")
//@TestPropertySource(locations = "classpath:application-h2.properties")
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;


    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamCreate.csv", delimiterString = ",", numLinesToSkip = 1)
    void createTask(Integer id) throws Exception {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        mvc.perform(post("/api/v1/tasks")
                        .contentType("application/json")
                        .content(taskRequestBodyList.get(id - 1))
                        .with(user("user").authorities(grantedAuthority))
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @Order(2)
    @WithMockUser(authorities="USER")
    @Test
    void findAllTasks() throws Exception {
        if (!taskResponseList.isEmpty()) {
            mvc.perform(get("/api/v1/tasks/list"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isNotEmpty());
        } else {
            mvc.perform(get("/api/v1/tasks/list"))
                    .andExpect(status().isNoContent());
        }
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamDelete.csv")
    void deleteByIdTask(Long id) throws Exception {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
        mvc.perform(delete("/api/v1/tasks/{id}", id)
                        .with(user("user").authorities(grantedAuthority))
                        .with(csrf())
                )
                .andExpect(status().isOk());
    }

    @Order(3)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamFindByDate.csv", delimiterString = ",", numLinesToSkip = 1)
    void findTasksBetweenDates(String startDateString, String endDateString, Integer result) throws Exception {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        if (result > 0) {
            mvc.perform(get("/api/v1/tasks/find_by_date")
                                    .with(user("user").authorities(grantedAuthority))
                                    .param("startDate", startDate.toString())
                                    .param("endDate", endDate.toString())
                    )
                .andExpectAll(status().isOk(), jsonPath("$.size()").value(result));
        } else {
            mvc.perform(get("/api/v1/tasks/find_by_date")
                                .with(user("user").authorities(grantedAuthority))
                                .param("startDate", startDate.toString())
                                .param("endDate", endDate.toString())
                )
                .andExpectAll(status().isNoContent());
        }
    }


    //create
    private final List<TaskResponse> taskResponseList = List.of(
            new TaskResponse(
                    1L,
                    "Задача № 1",
                    "Выполнить домашнее задание №1",
                    LocalDate.of(2025,4,28)
            ),
            new TaskResponse(
                    2L,
                    "Задача № 2",
                    "Выполнить домашнее задание №2",
                    LocalDate.of(2025,4,29)
            ),
            new TaskResponse(
                    3L,
                    "Задача № 3",
                    "Выполнить домашнее задание №3",
                    LocalDate.of(2025,4,30)
            )
    );

    private final List<String> taskRequestBodyList = List.of("""
                            {
                            	"name": "Задача № 1",
                                "description": "Выполнить домашнее задание №1",
                                "createdAt": "2025-04-28"
                            }
                        """,
            """
                            {
                            	"name": "Задача № 2",
                                "description": "Выполнить домашнее задание №2",
                                "createdAt": "2025-04-29"
                            }
                        """,
            """
                            {
                            	"name": "Задача № 3",
                                "description": "Выполнить домашнее задание №3",
                                "createdAt": "2025-04-30"
                            }
                        """
            );

}