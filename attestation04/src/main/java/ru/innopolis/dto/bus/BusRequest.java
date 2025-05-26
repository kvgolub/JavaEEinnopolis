package ru.innopolis.dto.bus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры записи Автобус</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Входящие данные Автобус")
public class BusRequest {

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
