package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Расписание содержит 6 методов:</p>
 *
 * <p>
 *     {@code createSchedule} - создать запись Расписание
 *     {@code findByIdSchedule} - найти запись Расписание по ID номеру
 *     {@code findAllSchedules} - найти все записи Расписание
 *     {@code updateSchedule} - обновить запись Расписание
 *     {@code deleteByIdSchedule} - удалить запись Расписание по ID номеру
 *     {@code deleteAllSchedules} - удалить все записи Расписание
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/schedule")
public interface ScheduleController {

    /**
     * Метод "создать запись Расписание"
     * @param scheduleRequest содержит основные входные параметры для записи Расписание
     * @return возвращается созданная запись Расписание
     */
    @Operation(summary = "Создание записи Расписание", description = "Метод \"Создать запись Расписание\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Расписание в теле запроса")
    @PostMapping
    ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest);

    /**
     * Метод "найти запись Расписание по ID номеру"
     * @param scheduleId содержит ID номер записи Расписание
     * @return возвращается найденная запись Расписание
     */
    @Operation(summary = "Поиск записи Расписание", description = "Метод \"Найти запись Расписание по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Расписание")
    @GetMapping("/{id}")
    ResponseEntity<Optional<ScheduleResponse>> findByIdSchedule(@Valid @PathVariable("id") Long scheduleId);

    /**
     * Метод "найти все записи Расписание"
     * @return возвращаются все записи Расписание
     */
    @Operation(summary = "Поиск всех записей Расписание", description = "Метод \"Найти все записи Расписание\"")
    @GetMapping("/list")
    ResponseEntity<List<ScheduleResponse>> findAllSchedules();

    /**
     * Метод "обновить запись Расписание"
     * @param id содержит ID номер записи Расписание
     * @param scheduleRequest содержит основные входные параметры для записи Расписание
     * @return возвращается измененная запись Расписание
     */
    @Operation(summary = "Обновление записи Расписание", description = "Метод \"Обновить запись Расписание\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Расписание")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Расписание в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<ScheduleResponse>> updateSchedule(@Valid @PathVariable Long id, @Valid @RequestBody ScheduleRequest scheduleRequest);

    /**
     * Метод "удалить запись Расписание по ID номеру"
     * @param scheduleId содержит ID номер записи Расписание
     * @return возвращается подтверждение об удалении записи Расписание
     */
    @Operation(summary = "Удаление записи Расписание", description = "Метод \"Удалить запись Расписание по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Расписание")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdSchedule(@Valid @PathVariable("id") Long scheduleId);

    /**
     * Метод "удалить все записи Расписание"
     * @return возвращается количество удаленных записей Расписание
     */
    @Operation(summary = "Удаление всех записей Расписание", description = "Метод \"Удалить все записи Расписание\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllSchedules();
}
