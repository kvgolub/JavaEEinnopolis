package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Автобус содержит 7 методов:</p>
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
public interface BusService {

    /**
     * Метод "создать запись Автобус"
     * @param busRequest содержит основные входные параметры для записи Автобус
     * @return возвращается созданная запись Автобус
     */
    BusResponse createBus(BusRequest busRequest);

    /**
     * Метод "найти запись Автобус по ID номеру"
     * @param id содержит ID номер записи Автобус
     * @return возвращается найденная запись Автобус
     */
    @Cacheable(value = "findByIdBus")
    Optional<BusResponse> findByIdBus(Long id);

    /**
     * Метод "найти все записи Автобус"
     * @return возвращаются все записи Автобус
     */
    @Cacheable(value = "findAllBuses")
    List<BusResponse> findAllBuses();

    /**
     * Метод "обновить запись Автобус"
     * @param id содержит ID номер записи Автобус
     * @param busRequest содержит основные входные параметры для записи Автобус
     * @return возвращается измененная запись Автобус
     */
    Optional<BusResponse> updateBus(Long id, BusRequest busRequest);

    /**
     * Метод "удалить запись Автобус по ID номеру"
     * @param id содержит ID номер записи Автобус
     * @return возвращается подтверждение об удалении записи Автобус
     */
    Boolean deleteByIdBus(Long id);

    /**
     * Метод "удалить все записи Автобус"
     * @return возвращается количество удаленных записей Автобус
     */
    Integer deleteAllBuses();


    /**
     * Метод "загрузить файл записей Автобус"
     * @param file содержит файл записей Автобус
     * @return возвращается подтверждение о загрузке записей Автобус
     */
    Boolean uploadFileBuses (MultipartFile file);
}
