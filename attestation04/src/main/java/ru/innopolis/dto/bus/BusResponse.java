package ru.innopolis.dto.bus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Автобус</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Автобус")
public class BusResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Тип
     */
    @Schema(description = "Тип")
    private Long type;

    /**
     * Модель
     */
    @Schema(description = "Модель")
    private String model;

    /**
     * Регистрационный номер
     */
    @Schema(description = "Регистрационный номер")
    private String regNumber;

    /**
     * Пассажировместимость
     */
    @Schema(description = "Пассажировместимость")
    private Integer passengerCapacity;

    /**
     * Цена
     */
    @Schema(description = "Цена")
    private Double price;
}
