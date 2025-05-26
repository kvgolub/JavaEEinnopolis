package ru.innopolis.dto.route;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Маршрут</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Маршрут")
public class RouteResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Номер
     */
    @Schema(description = "Номер")
    private String number;

    /**
     * Остановка отправления
     */
    @Schema(description = "Остановка отправления")
    private Long startStation;

    /**
     * Остановка прибытия
     */
    @Schema(description = "Остановка прибытия")
    private Long endStation;

    /**
     * Количество остановок
     */
    @Schema(description = "Количество остановок")
    private Integer quantityStation;

    /**
     * Пассажиропоток
     */
    @Schema(description = "Пассажиропоток")
    private Integer passengerFlow;
}
