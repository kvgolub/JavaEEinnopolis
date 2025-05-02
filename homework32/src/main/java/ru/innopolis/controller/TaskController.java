package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

import java.util.List;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс КонтроллерЗадачи содержит 3 метода:</p>
 *
 * <p>
 *     {@code createTask} - создать задачу
 *     {@code findAllTasks} - найти все задачи
 *     {@code deleteByIdTask} - удалить задачу по ID номеру
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/tasks")
public interface TaskController {

    /**
     * Метод "Создать задачу"
     * @param taskRequest содержит основные параметры задачи
     * @return возвращается запись о созданной задаче
     */
    @Operation(summary = "Создание задачи", description = "Метод \"Создать задачу\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные параметры задачи в теле запроса")
    @PostMapping
    ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest);

    /**
     * Метод "Найти все задачи"
     * @return возвращается список всех задач
     */
    @Operation(summary = "Поиск всех задач", description = "Метод \"Найти все задачи\"")
    @GetMapping
    ResponseEntity<List<TaskResponse>> findAllTasks();

    /**
     * Метод "Удалить задачу по ID номеру"
     * @param id идентификатор задачи
     * @return метод ничего не возвращает
     */
    @Operation(summary = "Удаление задачи", description = "Метод \"Удалить задачу по ID номеру\"")
    @Parameter(name = "id", description = "Идентификатор удаляемой задачи")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteByIdTask(@PathVariable Long id);

}
