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
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Водитель содержит 7 методов:</p>
 *
 * <p>
 *     {@code createDriver} - создать запись Водитель
 *     {@code findByIdDriver} - найти запись Водитель по ID номеру
 *     {@code findAllDrivers} - найти все записи Водитель
 *     {@code updateDriver} - обновить запись Водитель
 *     {@code deleteByIdDriver} - удалить запись Водитель по ID номеру
 *     {@code deleteAllDrivers} - удалить все записи Водитель
 *     {@code uploadFileDrivers} - загрузить файл записей Водитель
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/driver")
public interface DriverController {

    /**
     * Метод "создать запись Водитель"
     * @param driverRequest содержит основные входные параметры для записи Водитель
     * @return возвращается созданная запись Водитель
     */
    @Operation(summary = "Создание записи Водитель", description = "Метод \"Создать запись Водитель\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Водитель в теле запроса")
    @PostMapping
    ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest driverRequest);

    /**
     * Метод "найти запись Водитель по ID номеру"
     * @param driverId содержит ID номер записи Водитель
     * @return возвращается найденная запись Водитель
     */
    @Operation(summary = "Поиск записи Водитель", description = "Метод \"Найти запись Водитель по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Водитель")
    @GetMapping("/{id}")
    ResponseEntity<Optional<DriverResponse>> findByIdDriver(@Valid @PathVariable("id") Long driverId);

    /**
     * Метод "найти все записи Водитель"
     * @return возвращаются все записи Водитель
     */
    @Operation(summary = "Поиск всех записей Водитель", description = "Метод \"Найти все записи Водитель\"")
    @GetMapping("/list")
    ResponseEntity<List<DriverResponse>> findAllDrivers();

    /**
     * Метод "обновить запись Водитель"
     * @param id содержит ID номер записи Водитель
     * @param driverRequest содержит основные входные параметры для записи Водитель
     * @return возвращается измененная запись Водитель
     */
    @Operation(summary = "Обновление записи Водитель", description = "Метод \"Обновить запись Водитель\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Водитель")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Основные входные параметры для записи Водитель в теле запроса")
    @PutMapping("/{id}")
    ResponseEntity<Optional<DriverResponse>> updateDriver(@Valid @PathVariable Long id, @Valid @RequestBody DriverRequest driverRequest);

    /**
     * Метод "удалить запись Водитель по ID номеру"
     * @param driverId содержит ID номер записи Водитель
     * @return возвращается подтверждение об удалении записи Водитель
     */
    @Operation(summary = "Удаление записи Водитель", description = "Метод \"Удалить запись Водитель по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Водитель")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdDriver(@Valid @PathVariable("id") Long driverId);

    /**
     * Метод "удалить все записи Водитель"
     * @return возвращается количество удаленных записей Водитель
     */
    @Operation(summary = "Удаление всех записей Водитель", description = "Метод \"Удалить все записи Водитель\"")
    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllDrivers();


    /**
     * Метод "загрузить файл записей Водитель"
     * @param file содержит файл записей Водитель
     * @return возвращается подтверждение о загрузке записей Водитель
     */
    @Operation(summary = "Загрузка данных для записей Водитель", description = "Метод \"Загрузить данные для записей Водитель из файла\"")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Файл с данными для записей Водитель",
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
    ResponseEntity<Boolean> uploadFileDrivers(@RequestPart MultipartFile file) throws IOException;
}
