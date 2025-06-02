package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Автобусная остановка содержит 6 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Автобусная остановка
 *     {@code findByIdBus} - найти запись Автобусная остановка по ID номеру
 *     {@code findAllBuses} - найти все записи Автобусная остановка
 *     {@code updateBus} - обновить запись Автобусная остановка
 *     {@code deleteByIdBus} - удалить запись Автобусная остановка по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Автобусная остановка
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface BusStopService {

    /**
     * Метод "создать запись Автобусная остановка"
     * @param busStopRequest содержит основные входные параметры для записи Автобусная остановка
     * @return возвращается созданная запись Автобусная остановка
     */
    BusStopResponse createBusStop(BusStopRequest busStopRequest);

    /**
     * Метод "найти запись Автобусная остановка по ID номеру"
     * @param id содержит ID номер записи Автобусная остановка
     * @return возвращается найденная запись Автобусная остановка
     */
    @Cacheable(value = "findByIdBusStop")
    Optional<BusStopResponse> findByIdBusStop(Long id);

    /**
     * Метод "найти все записи Автобусная остановка"
     * @return возвращаются все записи Автобусная остановка
     */
    @Cacheable(value = "findAllBusStops")
    List<BusStopResponse> findAllBusStops();

    /**
     * Метод "обновить запись Автобусная остановка"
     * @param id содержит ID номер записи Автобусная остановка
     * @param busStopRequest содержит основные входные параметры для записи Автобусная остановка
     * @return возвращается измененная запись Автобусная остановка
     */
    Optional<BusStopResponse> updateBusStop(Long id, BusStopRequest busStopRequest);

    /**
     * Метод "удалить запись Автобусная остановка по ID номеру"
     * @param id содержит ID номер записи Автобусная остановка
     * @return возвращается подтверждение об удалении записи Автобусная остановка
     */
    Boolean deleteByIdBusStop(Long id);


    /**
     * Метод "удалить все записи Автобусная остановка"
     * @return возвращается количество удаленных запись Автобусная остановка
     */
    Integer deleteAllBusStops();
}
