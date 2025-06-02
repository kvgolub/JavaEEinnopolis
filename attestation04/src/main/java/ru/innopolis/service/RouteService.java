package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Маршрут содержит 7 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Маршрут
 *     {@code findByIdBus} - найти запись Маршрут по ID номеру
 *     {@code findAllBuses} - найти все записи Маршрут
 *     {@code updateBus} - обновить запись Маршрут
 *     {@code deleteByIdBus} - удалить запись Маршрут по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Маршрут
 *     {@code uploadFileBuses} - загрузить файл записей Маршрут
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface RouteService {

    /**
     * Метод "создать запись Маршрут"
     * @param routeRequest содержит основные входные параметры для записи Маршрут
     * @return возвращается созданная запись Маршрут
     */
    RouteResponse createRoute(RouteRequest routeRequest);

    /**
     * Метод "найти запись Маршрут по ID номеру"
     * @param id содержит ID номер записи Маршрут
     * @return возвращается найденная запись Маршрут
     */
    @Cacheable(value = "findByIdRoute")
    Optional<RouteResponse> findByIdRoute(Long id);

    /**
     * Метод "найти все записи Маршрут"
     * @return возвращаются все записи Маршрут
     */
    @Cacheable(value = "findAllRoutes")
    List<RouteResponse> findAllRoutes();

    /**
     * Метод "обновить запись Маршрут"
     * @param id содержит ID номер записи Маршрут
     * @param routeRequest содержит основные входные параметры для записи Маршрут
     * @return возвращается измененная запись Маршрут
     */
    Optional<RouteResponse> updateRoute(Long id, RouteRequest routeRequest);

    /**
     * Метод "удалить запись Маршрут по ID номеру"
     * @param id содержит ID номер записи Маршрут
     * @return возвращается подтверждение об удалении записи Маршрут
     */
    Boolean deleteByIdRoute(Long id);

    /**
     * Метод "удалить все записи Маршрут"
     * @return возвращается количество удаленных записей Маршрут
     */
    Integer deleteAllRoutes();


    /**
     * Метод "загрузить файл записей Маршрут"
     * @param file содержит файл записей Маршрут
     * @return возвращается подтверждение о загрузке записей Маршрут
     */
    Boolean uploadFileRoutes (MultipartFile file) throws IOException;
}
