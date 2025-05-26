package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Маршрут</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "route", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Номер
     */
    @Column(name = "number")
    private String number;

    /**
     * Остановка отправления
     */
    @Column(name = "start_station")
    private Long startStation;

    /**
     * Остановка прибытия
     */
    @Column(name = "end_station")
    private Long endStation;

    /**
     * Количество станций
     */
    @Column(name = "quantity_station")
    private Integer quantityStation;

    /**
     * Пассажиропоток
     */
    @Column(name = "passenger_flow")
    private Integer passengerFlow;
}
