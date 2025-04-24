package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * <h6>Класс для таблицы Маршруты.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи маршрута
 *     <br>{@code number} - Номер маршрута
 *     <br>{@code startStation} - Остановка отправления на маршруте
 *     <br>{@code endStation} - Остановка прибытия на маршруте
 *     <br>{@code quantityStation} - Количество остановок на маршруте
 *     <br>{@code passengerFlow} - Пассажиропоток на маршруте
 * </p>
 *
 * <p>
 *     Описание методов класса:
 *     <br>{@code getLeastQuantityStation} - Получить наименьшее количество станций
 * </p>
 */
@Entity
@Table(name = "route", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "start_station")
    private Long startStation;

    @Column(name = "end_station")
    private Long endStation;

    @Column(name = "quantity_station")
    private Integer quantityStation;

    @Column(name = "passenger_flow")
    private Integer passengerFlow;

    public Route(String number, Integer quantityStation) {
        this.number = number;
        this.quantityStation = quantityStation;
    }

    public String getLeastQuantityStation() {
        return "Route(number=" + number + ", quantityStation=" + quantityStation + ")";
    }

}
