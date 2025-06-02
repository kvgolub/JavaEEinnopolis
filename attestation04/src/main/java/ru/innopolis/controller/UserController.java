package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.user.UserResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Контроллера
 *
 * <p>Интерфейс Контроллера Пользователь содержит 2 метода:</p>
 *
 * <p>
 *     {@code findByIdUser} - найти запись Пользователь по ID номеру
 *     {@code findAllUsers} - найти все записи Пользователь
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@RequestMapping("/api/v1/bus_station/user")
public interface UserController {

    /**
     * Метод "найти запись Пользователь по ID номеру"
     * @param userName содержит ID номер записи Пользователь
     * @return возвращается найденная запись Пользователь
     */
    @Operation(summary = "Поиск записи Пользователь", description = "Метод \"Найти запись Пользователь по ID номеру\"")
    @Parameter(name = "id", description = "Идентификационный номер записи Пользователь")
    @GetMapping("/{username}")
    ResponseEntity<Optional<UserResponse>> findByIdUser(@Valid @PathVariable("username") String userName);

    /**
     * Метод "найти все записи Пользователь"
     * @return возвращаются все записи Пользователь
     */
    @Operation(summary = "Поиск всех записей Пользователь", description = "Метод \"Найти все записи Пользователь\"")
    @GetMapping("/list")
    ResponseEntity<List<UserResponse>> findAllUsers();
}
