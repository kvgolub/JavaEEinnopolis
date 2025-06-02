package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import ru.innopolis.dto.user.UserResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Пользователь содержит 2 метода:</p>
 *
 * <p>
 *     {@code findByIdUser} - найти запись Пользователь по ID номеру
 *     {@code findAllUsers} - найти все записи Пользователь
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface UserService {

    /**
     * Метод "найти запись Пользователь по ID номеру"
     * @param userName содержит ID номер записи Пользователь
     * @return возвращается найденная запись Пользователь
     */
    @Cacheable(value = "findByIdUser")
    Optional<UserResponse> findByIdUser(String userName);

    /**
     * Метод "найти все записи Пользователь"
     * @return возвращаются все записи Пользователь
     */
    @Cacheable(value = "findAllUsers")
    List<UserResponse> findAllUsers();
}
