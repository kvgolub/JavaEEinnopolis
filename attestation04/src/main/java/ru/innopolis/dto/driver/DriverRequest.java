package ru.innopolis.dto.driver;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры записи Водитель</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Входящие данные Водитель")
public class DriverRequest {

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
