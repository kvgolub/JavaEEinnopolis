package ru.innopolis.dto.task;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры задачи</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

    /**
     * Наименование задачи
     */
    @Schema(description = "Наименование задачи")
    private String name;

    /**
     * Описание задачи
     */
    @Schema(description = "Описание задачи")
    private String description;

    /**
     * Дата создания задачи
     */
    @Schema(description = "Дата создания задачи")
    private Date createdAt;

}
