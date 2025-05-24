package ru.innopolis.service;

import org.springframework.cache.annotation.Cacheable;
import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

import java.time.LocalDate;
import java.util.List;


/**
 * Слой Сервиса
 *
 * <p>Интерфейс СервисЗадачи содержит 3 метода:</p>
 *
 * <p>
 *     {@code createTask} - создать задачу
 *     {@code findAllTasks} - найти все задачи
 *     {@code deleteByIdTask} - удалить задачу по ID номеру
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
public interface TaskService {

    /**
     * Метод "Создать задачу"
     * @param taskRequest содержит основные параметры задачи
     * @return возвращается запись о созданной задаче
     */
    TaskResponse createTask(TaskRequest taskRequest);

    /**
     * Метод "Найти все задачи"
     * @return возвращается список всех задач
     */
    @Cacheable(value = "findAllTasks")
    List<TaskResponse> findAllTasks();

    /**
     * Метод "Удалить задачу по ID номеру"
     * @param id идентификатор задачи
     */
    void deleteByIdTask(Long id);

    /**
     * Метод "Найти задачи в промежутке дат"
     * @return возвращается список задач в промежутке дат
     */
    List<TaskResponse> findTasksBetweenDates(LocalDate startDate, LocalDate endDate);

}
