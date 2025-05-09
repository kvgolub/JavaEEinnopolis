package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

import java.time.LocalDate;
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
@RequestMapping("/api/v1/tasks")
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
    @GetMapping("/list")
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


    /**
     * Метод "Найти задачи в промежутке дат"
     * @return возвращается список задач в промежутке дат
     */
    @Operation(summary = "Поиск задач в промежутке дат", description = "Метод \"Найти задачи в промежутке дат\"")
    @GetMapping("/find_by_date")
    ResponseEntity<List<TaskResponse>> findTasksBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate);

}
