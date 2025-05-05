package ru.innopolis.service;

import ru.innopolis.dto.task.TaskRequest;
import ru.innopolis.dto.task.TaskResponse;

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
    List<TaskResponse> findAllTasks();

    /**
     * Метод "Удалить задачу по ID номеру"
     * @param id идентификатор задачи
     */
    void deleteByIdTask(Long id);

}
