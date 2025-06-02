package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Тип Автобуса содержит 6 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Тип Автобуса
 *     {@code findByIdBus} - найти запись Тип Автобуса по ID номеру
 *     {@code findAllBuses} - найти все записи Тип Автобуса
 *     {@code updateBus} - обновить запись Тип Автобуса
 *     {@code deleteByIdBus} - удалить запись Тип Автобуса по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Тип Автобуса
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface BusTypeService {

    /**
     * Метод "создать запись Тип Автобуса"
     * @param busTypeRequest содержит основные входные параметры для записи Тип Автобуса
     * @return возвращается созданная запись Тип Автобуса
     */
    BusTypeResponse createBusType(BusTypeRequest busTypeRequest);

    /**
     * Метод "найти запись Тип Автобуса по ID номеру"
     * @param id содержит ID номер записи Тип Автобуса
     * @return возвращается найденная запись Тип Автобуса
     */
    @Cacheable(value = "findByIdBusType")
    Optional<BusTypeResponse> findByIdBusType(Long id);

    /**
     * Метод "найти все записи Тип Автобуса"
     * @return возвращаются все записи Тип Автобуса
     */
    @Cacheable(value = "findAllBusTypes")
    List<BusTypeResponse> findAllBusTypes();

    /**
     * Метод "обновить запись Тип Автобуса"
     * @param id содержит ID номер записи Тип Автобуса
     * @param busTypeRequest содержит основные входные параметры для записи Тип Автобуса
     * @return возвращается измененная запись Тип Автобуса
     */
    Optional<BusTypeResponse> updateBusType(Long id, BusTypeRequest busTypeRequest);

    /**
     * Метод "удалить запись Тип Автобуса по ID номеру"
     * @param id содержит ID номер записи Тип Автобуса
     * @return возвращается подтверждение об удалении записи Тип Автобуса
     */
    Boolean deleteByIdBusType(Long id);


    /**
     * Метод "удалить все записи Тип Автобуса"
     * @return возвращается количество удаленных запись Тип Автобуса
     */
    Integer deleteAllBusTypes();
}
