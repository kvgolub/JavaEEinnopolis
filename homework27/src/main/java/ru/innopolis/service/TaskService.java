package ru.innopolis.service;

import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

import java.util.List;


public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);
    List<TaskResponse> findAllTasks();
    void deleteByIdTask(Long id);

}
