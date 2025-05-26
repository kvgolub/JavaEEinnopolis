package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Автобусная остановка содержит 6 методов:</p>
 *
 * <p>
 *     {@code createBusStop} - создать запись Автобусная остановка
 *     {@code findByIdBusStop} - найти запись Автобусная остановка по ID номеру
 *     {@code findAllBusStops} - найти все записи Автобусная остановка
 *     {@code updateBusStop} - обновить запись Автобусная остановка
 *     {@code deleteByIdBusStop} - удалить запись Автобусная остановка по ID номеру
 *     {@code deleteAllBusStops} - удалить все записи Автобусная остановка
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/bus_stop")
public interface BusStopController {

    /**
     * Метод "создать запись Автобусная остановка"
     * @param busStopRequest содержит основные входные параметры для записи Автобусная остановка
     * @return возвращается созданная запись Автобусная остановка
     */
    @Operation(summary = "Создание записи Автобусная остановка", description = "Метод \"Создать запись Автобусная остановка\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Автобусная остановка в теле запроса")
    @PostMapping
    ResponseEntity<BusStopResponse> createBusStop(@Valid @RequestBody BusStopRequest busStopRequest);

    /**
     * Метод "найти запись Автобусная остановка по ID номеру"
     * @param busStopId содержит ID номер записи Автобусная остановка
     * @return возвращается найденная запись Автобусная остановка
     */
    @Operation(summary = "Поиск записи Автобусная остановка", description = "Метод \"Найти запись Автобусная остановка по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобусная остановка")
    @GetMapping("/{id}")
    ResponseEntity<Optional<BusStopResponse>> findByIdBusStop(@Valid @PathVariable("id") Long busStopId);

    /**
     * Метод "найти все записи Автобусная остановка"
     * @return возвращаются все записи Автобусная остановка
     */
    @Operation(summary = "Поиск всех записей Автобусная остановка", description = "Метод \"Найти все записи Автобусная остановка\"")
    @GetMapping("/list")
    ResponseEntity<List<BusStopResponse>> findAllBusStops();

    /**
     * Метод "обновить запись Автобусная остановка"
     * @param id содержит ID номер записи Автобусная остановка
     * @param busStopRequest содержит основные входные параметры для записи Автобусная остановка
     * @return возвращается измененная запись Автобусная остановка
     */
    @Operation(summary = "Обновление записи Автобусная остановка", description = "Метод \"Обновить запись Автобусная остановка\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобусная остановка")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Автобусная остановка в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<BusStopResponse>> updateBusStop(@Valid @PathVariable Long id, @Valid @RequestBody BusStopRequest busStopRequest);

    /**
     * Метод "удалить запись Автобусная остановка по ID номеру"
     * @param busStopId содержит ID номер записи Автобусная остановка
     * @return возвращается подтверждение об удалении записи Автобусная остановка
     */
    @Operation(summary = "Удаление записи Автобусная остановка", description = "Метод \"Удалить запись Автобусная остановка по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобусная остановка")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBusStop(@Valid @PathVariable("id") Long busStopId);

    /**
     * Метод "удалить все записи Автобусная остановка"
     * @return возвращается количество удаленных запись Автобусная остановка
     */
    @Operation(summary = "Удаление всех записей Автобусная остановка", description = "Метод \"Удалить все записи Автобусная остановка\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBusStops();
}
