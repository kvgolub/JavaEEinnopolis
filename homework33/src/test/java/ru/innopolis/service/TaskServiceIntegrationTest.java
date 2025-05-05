package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.entity.Task;
import ru.innopolis.repository.TaskRepository;
import ru.innopolis.service.impl.TaskServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableJpaRepositories(basePackageClasses = TaskRepository.class)
@EntityScan(basePackageClasses = Task.class)
@AutoConfigureDataJpa
@SpringBootTest(classes = {TaskServiceImpl.class})
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskServiceImpl taskService;


    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamCreate.csv", delimiterString = ",", numLinesToSkip = 1)
    void createTaskServiceTest(Long id, String name, String description, String createdAt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        TaskRequest taskRequest = new TaskRequest(
                name,
                description,
                sdf.parse(createdAt)
        );

        var response = taskService.createTask(taskRequest);

        assertThat("Тестирование \"Создание заявки\"", response.getId(), equalTo(id));
    }

    @Order(2)
    @Test
    void findAllTasks() {
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Поиск всех заявок\"", taskResponseList.size(), equalTo(3));
    }

    @Order(3)
    @ParameterizedTest
    @CsvFileSource(resources = "/testParamDelete.csv")
    void deleteByIdTask(Long id) {
        taskService.deleteByIdTask(id);
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        assertThat("Тестирование \"Удаление заявки\"", true, is(taskResponseList.stream().filter(taskResponse ->
                Objects.equals(taskResponse.getId(), id)).findFirst().isEmpty())
        );
    }

}
