package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Водитель содержит 7 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Водитель
 *     {@code findByIdBus} - найти запись Водитель по ID номеру
 *     {@code findAllBuses} - найти все записи Водитель
 *     {@code updateBus} - обновить запись Водитель
 *     {@code deleteByIdBus} - удалить запись Водитель по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Водитель
 *     {@code uploadFileBuses} - загрузить файл записей Водитель
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface DriverService {

    /**
     * Метод "создать запись Водитель"
     * @param driverRequest содержит основные входные параметры для записи Водитель
     * @return возвращается созданная запись Водитель
     */
    DriverResponse createDriver(DriverRequest driverRequest);

    /**
     * Метод "найти запись Водитель по ID номеру"
     * @param id содержит ID номер записи Водитель
     * @return возвращается найденная запись Водитель
     */
    @Cacheable(value = "findByIdDriver")
    Optional<DriverResponse> findByIdDriver(Long id);

    /**
     * Метод "найти все записи Водитель"
     * @return возвращаются все записи Водитель
     */
    @Cacheable(value = "findAllDrivers")
    List<DriverResponse> findAllDrivers();

    /**
     * Метод "обновить запись Водитель"
     * @param id содержит ID номер записи Водитель
     * @param driverRequest содержит основные входные параметры для записи Водитель
     * @return возвращается измененная запись Водитель
     */
    Optional<DriverResponse> updateDriver(Long id, DriverRequest driverRequest);

    /**
     * Метод "удалить запись Водитель по ID номеру"
     * @param id содержит ID номер записи Водитель
     * @return возвращается подтверждение об удалении записи Водитель
     */
    Boolean deleteByIdDriver(Long id);

    /**
     * Метод "удалить все записи Водитель"
     * @return возвращается количество удаленных записей Водитель
     */
    Integer deleteAllDrivers();


    /**
     * Метод "загрузить файл записей Водитель"
     * @param file содержит файл записей Водитель
     * @return возвращается подтверждение о загрузке записей Водитель
     */
    Boolean uploadFileDrivers (MultipartFile file) throws IOException;
}
