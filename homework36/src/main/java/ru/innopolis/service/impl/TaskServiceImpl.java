package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;
import ru.innopolis.entity.Task;
import ru.innopolis.repository.TaskRepository;
import ru.innopolis.service.TaskService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(
                new Task(
                        null,
                        taskRequest.getName(),
                        taskRequest.getDescription(),
                        taskRequest.getCreatedAt()
                )
        );

        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCreatedAt()
        );
    }

    @Override
    public List<TaskResponse> findAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        taskList.forEach(task -> taskResponseList.add(
                new TaskResponse(
                        task.getId(),
                        task.getName(),
                        task.getDescription(),
                        task.getCreatedAt()
                )
        ));

        return taskResponseList;
    }

    @Override
    public void deleteByIdTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponse> findTasksBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Task> taskList = taskRepository.findTasksByCreatedAtBetween(startDate, endDate);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        taskList.forEach(task -> taskResponseList.add(
                new TaskResponse(
                        task.getId(),
                        task.getName(),
                        task.getDescription(),
                        task.getCreatedAt()
                )
        ));

        return taskResponseList;
    }

}
