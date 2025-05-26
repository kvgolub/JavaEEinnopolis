package ru.innopolis.dto.busType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры записи Тип автобуса</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Входящие данные Тип автобуса")
public class BusTypeRequest {

    /**
     * Наименование типа
     */
    @Schema(description = "Наименование типа")
    private String nameType;
}
