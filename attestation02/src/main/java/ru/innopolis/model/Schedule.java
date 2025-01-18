package ru.innopolis.model;

import lombok.*;
import java.util.Date;


/**
 * <h6>Класс для таблицы Расписание.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи расписания
 *     <br>{@code routeDate} - Дата поездки
 *     <br>{@code routeId} - Связь с записью маршрута
 *     <br>{@code busId} - Связь с записью автобуса
 *     <br>{@code driverId} - Связь с записью водителя
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule {
    private Integer id;
    private Date routeDate;
    private Integer routeId;
    private Integer busId;
    private Integer driverId;
}
