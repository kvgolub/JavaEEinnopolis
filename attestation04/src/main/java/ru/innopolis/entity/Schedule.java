package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Расписание</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "schedule", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedule {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Дата маршрута
     */
    @Column(name = "route_date")
    private LocalDateTime routeDate;

    /**
     * Идентификационный номер маршрута
     */
    @Column(name = "route_id")
    private Long routeId;

    /**
     * Идентификационный номер автобуса
     */
    @Column(name = "bus_id")
    private Long busId;

    /**
     * Идентификационный номер водителя
     */
    @Column(name = "driver_id")
    private Long driverId;
}
