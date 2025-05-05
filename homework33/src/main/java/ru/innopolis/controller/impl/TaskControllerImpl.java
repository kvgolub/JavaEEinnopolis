package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.TaskController;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.service.TaskService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;


    @Override
    public ResponseEntity<TaskResponse> createTask(TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @Override
    public ResponseEntity<List<TaskResponse>> findAllTasks() {
        List<TaskResponse> taskResponseList = taskService.findAllTasks();

        return !taskResponseList.isEmpty()
                ? ResponseEntity.ok().body(taskResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteByIdTask(Long id) {
        taskService.deleteByIdTask(id);

        return ResponseEntity.ok().build();
    }

}
