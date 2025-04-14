package ru.innopolis.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "schedule", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "route_date")
    private Date routeDate;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "bus_id")
    private Long busId;

    @Column(name = "driver_id")
    private Long driverId;

}
