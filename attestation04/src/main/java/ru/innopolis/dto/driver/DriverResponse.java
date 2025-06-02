package ru.innopolis.dto.driver;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Водитель</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Водитель")
public class DriverResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Фамилия
     */
    @Schema(description = "Фамилия")
    private String surname;

    /**
     * Имя
     */
    @Schema(description = "Имя")
    private String name;

    /**
     * Возраст
     */
    @Schema(description = "Возраст")
    private Integer age;
}
