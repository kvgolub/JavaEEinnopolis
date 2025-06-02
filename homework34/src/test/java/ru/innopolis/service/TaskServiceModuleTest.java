package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.entity.Task;
import ru.innopolis.repository.TaskRepository;
import ru.innopolis.service.impl.TaskServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


@ExtendWith(MockitoExtension.class)
public class TaskServiceModuleTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;


    @ParameterizedTest
    @CsvFileSource(resources = "/testParamCreate.csv", delimiterString = ",", numLinesToSkip = 1)
    void createTaskServiceTest(Long id, String name, String description, String createdAt) {
        LocalDate localDate = LocalDate.parse(createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        TaskRequest taskRequest = new TaskRequest(
                name,
                description,
                localDate
        );

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(taskList.stream().filter(task ->
                Objects.equals(task.getName(), name)).findFirst().get());
        var response = taskService.createTask(taskRequest);

        assertThat("Тестирование \"Создание заявки\"", response.getId(), equalTo(id));
    }


    @Test
    void findAllTasks() {
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Поиск всех задач\"", taskResponseList.size(), equalTo(3));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testParamDelete.csv")
    void deleteByIdTask(Long id) {
        Mockito.doNothing().when(taskRepository).deleteById(Mockito.any(Long.class));
        taskService.deleteByIdTask(id);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Удаление заявки\"", true, is(taskResponseList.stream().filter(taskResponse ->
                Objects.equals(taskResponse.getId(), id)).findFirst().isEmpty())
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testParamFindByDate.csv", delimiterString = ",", numLinesToSkip = 1)
    void findTasksBetweenDates(String startDateString, String endDateString, Integer result) {
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Mockito.when(taskRepository.findTasksByCreatedAtBetween(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(taskList.stream().filter(task ->
                startDate.datesUntil(endDate.plusDays(1)).anyMatch(localDate ->
                        localDate.equals(task.getCreatedAt())
                )
        ).toList());
        List<TaskResponse> taskResponseList = taskService.findTasksBetweenDates(startDate, endDate);

        assertThat("Тестирование \"Поиск задач в промежутке дат\"", taskResponseList.size(), equalTo(result));
    }


    private final List<Task> taskList = List.of(
            new Task(
                    1L,
                    "Задача № 1",
                    "Выполнить домашнее задание №1",
                    LocalDate.of(2025,4,28)
            ),
            new Task(
                    2L,
                    "Задача № 2",
                    "Выполнить домашнее задание №2",
                    LocalDate.of(2025,4,29)
            ),
            new Task(
                    3L,
                    "Задача № 3",
                    "Выполнить домашнее задание №3",
                    LocalDate.of(2025,4,30)
            )
    );

}
