package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;

import java.util.List;
import java.util.Optional;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс Сервиса Расписание содержит 6 методов:</p>
 *
 * <p>
 *     {@code createBus} - создать запись Расписание
 *     {@code findByIdBus} - найти запись Расписание по ID номеру
 *     {@code findAllBuses} - найти все записи Расписание
 *     {@code updateBus} - обновить запись Расписание
 *     {@code deleteByIdBus} - удалить запись Расписание по ID номеру
 *     {@code deleteAllBuses} - удалить все записи Расписание
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface ScheduleService {

    /**
     * Метод "создать запись Расписание"
     * @param scheduleRequest содержит основные входные параметры для записи Расписание
     * @return возвращается созданная запись Расписание
     */
    ScheduleResponse createSchedule(ScheduleRequest scheduleRequest);

    /**
     * Метод "найти запись Расписание по ID номеру"
     * @param id содержит ID номер записи Расписание
     * @return возвращается найденная запись Расписание
     */
    @Cacheable(value = "findByIdSchedule")
    Optional<ScheduleResponse> findByIdSchedule(Long id);

    /**
     * Метод "найти все записи Расписание"
     * @return возвращаются все записи Расписание
     */
    @Cacheable(value = "findAllSchedules")
    List<ScheduleResponse> findAllSchedules();

    /**
     * Метод "обновить запись Расписание"
     * @param id содержит ID номер записи Расписание
     * @param scheduleRequest содержит основные входные параметры для записи Расписание
     * @return возвращается измененная запись Расписание
     */
    Optional<ScheduleResponse> updateSchedule(Long id, ScheduleRequest scheduleRequest);

    /**
     * Метод "удалить запись Расписание по ID номеру"
     * @param id содержит ID номер записи Расписание
     * @return возвращается подтверждение об удалении записи Расписание
     */
    Boolean deleteByIdSchedule(Long id);

    /**
     * Метод "удалить все записи Расписание"
     * @return возвращается количество удаленных записей Расписание
     */
    Integer deleteAllSchedules();
}
