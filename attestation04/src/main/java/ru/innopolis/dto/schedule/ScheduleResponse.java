package ru.innopolis.dto.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Расписание</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Расписание")
public class ScheduleResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Дата маршрута
     */
    @Schema(description = "Дата маршрута")
    private LocalDateTime routeDate;

    /**
     * Идентификационный номер маршрута
     */
    @Schema(description = "Идентификационный номер маршрута")
    private Long routeId;

    /**
     * Идентификационный номер автобуса
     */
    @Schema(description = "Идентификационный номер автобуса")
    private Long busId;

    /**
     * Идентификационный номер водителя
     */
    @Schema(description = "Идентификационный номер водителя")
    private Long driverId;
}
