package ru.innopolis.dto.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры записи Расписание</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Входящие данные Расписание")
public class ScheduleRequest {

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
