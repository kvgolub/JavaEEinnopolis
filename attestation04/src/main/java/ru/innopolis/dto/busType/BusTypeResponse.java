package ru.innopolis.dto.busType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Тип автобуса</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Тип автобуса")
public class BusTypeResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Наименование типа
     */
    @Schema(description = "Наименование типа")
    private String nameType;
}
