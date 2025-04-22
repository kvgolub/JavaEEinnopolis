package ru.innopolis.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры задачи</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {

    /**
     * Идентификатор задачи
     */
    private Long id;

    /**
     * Наименование задачи
     */
    private String name;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * Дата создания задачи
     */
    private Date createdAt;

}
