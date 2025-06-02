package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;

import java.io.File;
import java.util.*;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Автобус содержит 7 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Автобус
 *     {@code findByIdBus} - найти запись Автобус по ID номеру
 *     {@code findAllBuses} - найти все записи Автобус
 *     {@code updateBus} - обновить запись Автобус
 *     {@code deleteByIdBus} - удалить запись Автобус по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Автобус
 *     {@code uploadFileBuses} - загрузить файл записей Автобус
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/bus")
public interface BusController {

    /**
     * Метод "создать запись Автобус"
     * @param busRequest содержит основные входные параметры для записи Автобус
     * @return возвращается созданная запись Автобус
     */
    @Operation(summary = "Создание записи Автобус", description = "Метод \"Создать запись Автобус\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Автобус в теле запроса")
    @PostMapping
    ResponseEntity<BusResponse> createBus(@Valid @RequestBody BusRequest busRequest);

    /**
     * Метод "найти запись Автобус по ID номеру"
     * @param busId содержит ID номер записи Автобус
     * @return возвращается найденная запись Автобус
     */
    @Operation(summary = "Поиск записи Автобус", description = "Метод \"Найти запись Автобус по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобус")
    @GetMapping("/{id}")
    ResponseEntity<Optional<BusResponse>> findByIdBus(@Valid @PathVariable("id") Long busId);

    /**
     * Метод "найти все записи Автобус"
     * @return возвращаются все записи Автобус
     */
    @Operation(summary = "Поиск всех записей Автобус", description = "Метод \"Найти все записи Автобус\"")
    @GetMapping("/list")
    ResponseEntity<List<BusResponse>> findAllBuses();

    /**
     * Метод "обновить запись Автобус"
     * @param id содержит ID номер записи Автобус
     * @param busRequest содержит основные входные параметры для записи Автобус
     * @return возвращается измененная запись Автобус
     */
    @Operation(summary = "Обновление записи Автобус", description = "Метод \"Обновить запись Автобус\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобус")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Автобус в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<BusResponse>> updateBus(@Valid @PathVariable Long id, @Valid @RequestBody BusRequest busRequest);

    /**
     * Метод "удалить запись Автобус по ID номеру"
     * @param busId содержит ID номер записи Автобус
     * @return возвращается подтверждение об удалении записи Автобус
     */
    @Operation(summary = "Удаление записи Автобус", description = "Метод \"Удалить запись Автобус по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Автобус")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdBus(@Valid @PathVariable("id") Long busId);

    /**
     * Метод "удалить все записи Автобус"
     * @return возвращается количество удаленных записей Автобус
     */
    @Operation(summary = "Удаление всех записей Автобус", description = "Метод \"Удалить все записи Автобус\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllBuses();


    /**
     * Метод "загрузить данные для записей Автобус из файла"
     * @param file содержит данные для записей Автобус
     * @return возвращается подтверждение о загрузке данных для записей Автобус
     */
    @Operation(summary = "Загрузка данных для записей Автобус", description = "Метод \"Загрузить данные для записей Автобус из файла\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Файл с данными для записей Автобус",
            content = {
                    @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    properties = {
                                            @StringToClassMapItem(
                                                    key = "file",
                                                    value = File.class
                                            )
                                    }
                            )
                    )
            }
    )
    @PostMapping(value = "/file")
    ResponseEntity<Boolean> uploadFileBuses(@RequestPart MultipartFile file);
}
