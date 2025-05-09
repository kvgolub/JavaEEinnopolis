package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.service.impl.TaskServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-postgres.properties")
//@TestPropertySource(locations = "classpath:application-h2.properties")
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskServiceImpl taskService;


    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamCreate.csv", delimiterString = ",", numLinesToSkip = 1)
    void createTaskServiceTest(Long id, String name, String description, String createdAt) {
        LocalDate localDate = LocalDate.parse(createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        TaskRequest taskRequest = new TaskRequest(
                name,
                description,
                localDate
        );

        var response = taskService.createTask(taskRequest);

        assertThat("Тестирование \"Создание заявки\"", response.getId(), equalTo(id));
    }

    @Order(2)
    @Test
    void findAllTasks() {
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Поиск всех задач\"", taskResponseList.size(), equalTo(3));
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamDelete.csv")
    void deleteByIdTask(Long id) {
        taskService.deleteByIdTask(id);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Удаление заявки\"", true, is(taskResponseList.stream().filter(taskResponse ->
                Objects.equals(taskResponse.getId(), id)).findFirst().isEmpty())
        );
    }

    @Order(3)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamFindByDate.csv", delimiterString = ",", numLinesToSkip = 1)
    void findTasksBetweenDates(String startDateString, String endDateString, Integer result) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        List<TaskResponse> taskResponseList = taskService.findTasksBetweenDates(startDate, endDate);

        assertThat("Тестирование \"Поиск задач в промежутке дат\"", taskResponseList.size(), equalTo(result));
    }

}
