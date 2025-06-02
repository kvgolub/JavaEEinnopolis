package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Тип Автобуса содержит 6 методов:</p>
 *
 * <p>
 *     {@code createBusType} - создать запись Тип Автобуса
 *     {@code findByIdBusType} - найти запись Тип Автобуса по ID номеру
 *     {@code findAllBusTypes} - найти все записи Тип Автобуса
 *     {@code updateBusType} - обновить запись Тип Автобуса
 *     {@code deleteByIdBusType} - удалить запись Тип Автобуса по ID номеру
 *     {@code deleteAllBusTypes} - удалить все записи Тип Автобуса
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/bus_type")
public interface BusTypeController {

    /**
     * Метод "создать запись Тип Автобуса"
     * @param busTypeRequest содержит основные входные параметры для записи Тип Автобуса
     * @return возвращается созданная запись Тип Автобуса
     */
    @Operation(summary = "Создание записи Тип Автобуса", description = "Метод \"Создать запись Тип Автобуса\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Тип Автобуса в теле запроса")
    @PostMapping
    ResponseEntity<BusTypeResponse> createBusType(@Valid @RequestBody BusTypeRequest busTypeRequest);

    /**
     * Метод "найти запись Тип Автобуса по ID номеру"
     * @param busTypeId содержит ID номер записи Тип Автобуса
     * @return возвращается найденная запись Тип Автобуса
     */
    @Operation(summary = "Поиск записи Тип Автобуса", description = "Метод \"Найти запись Тип Автобуса по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Тип Автобуса")
    @GetMapping("/{id}")
    ResponseEntity<Optional<BusTypeResponse>> findByIdBusType(@Valid @PathVariable("id") Long busTypeId);

    /**
     * Метод "найти все записи Тип Автобуса"
     * @return возвращаются все записи Тип Автобуса
     */
    @Operation(summary = "Поиск всех записей Тип Автобуса", description = "Метод \"Найти все записи Тип Автобуса\"")
    @GetMapping("/list")
    ResponseEntity<List<BusTypeResponse>> findAllBusTypes();

    /**
     * Метод "обновить запись Тип Автобуса"
     * @param id содержит ID номер записи Тип Автобуса
     * @param busTypeRequest содержит основные входные параметры для записи Тип Автобуса
     * @return возвращается измененная запись Тип Автобуса
     */
    @Operation(summary = "Обновление записи Тип Автобуса", description = "Метод \"Обновить запись Тип Автобуса\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Тип Автобуса")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Тип Автобуса в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<BusTypeResponse>> updateBusType(@Valid @PathVariable Long id, @Valid @RequestBody BusTypeRequest busTypeRequest);

    /**
     * Метод "удалить запись Тип Автобуса по ID номеру"
     * @param busTypeId содержит ID номер записи Тип Автобуса
     * @return возвращается подтверждение об удалении записи Тип Автобуса
     */
    @Operation(summary = "Удаление записи Тип Автобуса", description = "Метод \"Удалить запись Тип Автобуса по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Тип Автобуса")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBusType(@Valid @PathVariable("id") Long busTypeId);

    /**
     * Метод "удалить все записи Тип Автобуса"
     * @return возвращается количество удаленных запись Тип Автобуса
     */
    @Operation(summary = "Удаление всех записей Тип Автобуса", description = "Метод \"Удалить все записи Тип Автобуса\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBusTypes();
}
