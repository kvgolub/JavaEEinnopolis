package ru.innopolis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

import java.util.List;


@RequestMapping("/api/tasks")
public interface TaskController {

    @PostMapping
    ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest);

    @GetMapping
    ResponseEntity<List<TaskResponse>> findAllTasks();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteByIdTask(@PathVariable Long id);

}
