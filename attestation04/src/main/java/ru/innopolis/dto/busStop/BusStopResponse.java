package ru.innopolis.dto.busStop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


/**
 * Слой DTО
 *
 * <p>Класс содержит получаемые параметры записи Автобусная остановка</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Исходящие данные Автобусная остановка")
public class BusStopResponse implements Serializable {

    /**
     * Идентификационный номер записи
     */
    @Schema(description = "Идентификационный номер")
    private Long id;

    /**
     * Наименование остановки
     */
    @Schema(description = "Наименование остановки")
    private String nameStop;
}
