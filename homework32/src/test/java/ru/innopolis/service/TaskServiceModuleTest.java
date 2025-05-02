package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.entity.Task;
import ru.innopolis.repository.TaskRepository;
import ru.innopolis.service.impl.TaskServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@EnableJpaRepositories(basePackageClasses = TaskRepository.class)
@EntityScan(basePackageClasses = Task.class)
@AutoConfigureDataJpa
@SpringBootTest(classes = {TaskServiceImpl.class})
public class TaskServiceModuleTest {

    @Autowired
    private TaskServiceImpl taskService;

    @MockitoBean
    private TaskRepository taskRepository;


    @ParameterizedTest
    @CsvFileSource(resources = "/testParamCreate.csv", delimiterString = ",", numLinesToSkip = 1)
    void createTaskServiceTest(Long id, String name, String description, String createdAt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        TaskRequest taskRequest = new TaskRequest(
                name,
                description,
                sdf.parse(createdAt)
        );

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(taskList.stream().filter(task ->
                Objects.equals(task.getName(), name)).findFirst().get());
        var response = taskService.createTask(taskRequest);

        Assertions.assertEquals(id, response.getId());
    }


    @Test
    void findAllTasks() {
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        Assertions.assertEquals(3, taskResponseList.size());
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testParamDelete.csv")
    void deleteByIdTask(Long id) {
        Mockito.doNothing().when(taskRepository).deleteById(Mockito.any(Long.class));
        taskService.deleteByIdTask(id);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        Assertions.assertTrue(
                taskResponseList.stream().filter(taskResponse ->
                        Objects.equals(taskResponse.getId(), id)).findFirst().isEmpty()
        );
    }


    private List<Task> taskList = List.of(
            new Task(
                    1L,
                    "Задача № 1",
                    "Выполнить домашнее задание №1",
                    new Date()
            ),
            new Task(
                    2L,
                    "Задача № 2",
                    "Выполнить домашнее задание №2",
                    new Date()
            ),
            new Task(
                    3L,
                    "Задача № 3",
                    "Выполнить домашнее задание №3",
                    new Date()
            )
    );

}
