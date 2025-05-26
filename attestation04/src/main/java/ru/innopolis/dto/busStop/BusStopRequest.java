package ru.innopolis.dto.busStop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 * Слой DTО
 *
 * <p>Класс содержит передаваемые параметры записи Автобусная остановка</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Входящие данные Автобусная остановка")
public class BusStopRequest {

    /**
     * Наименование остановки
     */
    @Schema(description = "Наименование остановки")
    private String nameStop;
}
