package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Маршрут содержит 7 методов:</p>
 *
 * <p>
 *     {@code createRoute} - создать запись Маршрут
 *     {@code findByIdRoute} - найти запись Маршрут по ID номеру
 *     {@code findAllRoutes} - найти все записи Маршрут
 *     {@code updateRoute} - обновить запись Маршрут
 *     {@code deleteByIdRoute} - удалить запись Маршрут по ID номеру
 *     {@code deleteAllRoutes} - удалить все записи Маршрут
 *     {@code uploadFileRoutes} - загрузить файл записей Маршрут
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/route")
public interface RouteController {

    /**
     * Метод "создать запись Маршрут"
     * @param routeRequest содержит основные входные параметры для записи Маршрут
     * @return возвращается созданная запись Маршрут
     */
    @Operation(summary = "Создание записи Маршрут", description = "Метод \"Создать запись Маршрут\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Маршрут в теле запроса")
    @PostMapping
    ResponseEntity<RouteResponse> createRoute(@Valid @RequestBody RouteRequest routeRequest);

    /**
     * Метод "найти запись Маршрут по ID номеру"
     * @param routeId содержит ID номер записи Маршрут
     * @return возвращается найденная запись Маршрут
     */
    @Operation(summary = "Поиск записи Маршрут", description = "Метод \"Найти запись Маршрут по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Маршрут")
    @GetMapping("/{id}")
    ResponseEntity<Optional<RouteResponse>> findByIdRoute(@Valid @PathVariable("id") Long routeId);

    /**
     * Метод "найти все записи Маршрут"
     * @return возвращаются все записи Маршрут
     */
    @Operation(summary = "Поиск всех записей Маршрут", description = "Метод \"Найти все записи Маршрут\"")
    @GetMapping("/list")
    ResponseEntity<List<RouteResponse>> findAllRoutes();

    /**
     * Метод "обновить запись Маршрут"
     * @param id содержит ID номер записи Маршрут
     * @param routeRequest содержит основные входные параметры для записи Маршрут
     * @return возвращается измененная запись Маршрут
     */
    @Operation(summary = "Обновление записи Маршрут", description = "Метод \"Обновить запись Маршрут\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Маршрут")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Маршрут в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<RouteResponse>> updateRoute(@Valid @PathVariable Long id, @Valid @RequestBody RouteRequest routeRequest);

    /**
     * Метод "удалить запись Маршрут по ID номеру"
     * @param routeId содержит ID номер записи Маршрут
     * @return возвращается подтверждение об удалении записи Маршрут
     */
    @Operation(summary = "Удаление записи Маршрут", description = "Метод \"Удалить запись Маршрут по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Маршрут")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdRoute(@Valid @PathVariable("id") Long routeId);

    /**
     * Метод "удалить все записи Маршрут"
     * @return возвращается количество удаленных записей Маршрут
     */
    @Operation(summary = "Удаление всех записей Маршрут", description = "Метод \"Удалить все записи Маршрут\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllRoutes();


    /**
     * Метод "загрузить файл записей Маршрут"
     * @param file содержит файл записей Маршрут
     * @return возвращается подтверждение о загрузке записей Маршрут
     */
    @Operation(summary = "Загрузка данных для записей Маршрут", description = "Метод \"Загрузить данные для записей Маршрут из файла\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Файл с данными для записей Маршрут",
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
    ResponseEntity<Boolean> uploadFileRoutes(@RequestPart MultipartFile file) throws IOException;
}
