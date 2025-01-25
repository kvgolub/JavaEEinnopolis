package ru.innopolis.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Route {
    private Integer id;
    private String number;
    private Integer startStation;
    private Integer endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

    public Route(String number, Integer quantityStation) {
        this.number = number;
        this.quantityStation = quantityStation;
    }

    public String getLeastQuantityStation() {
        return "Route(number=" + number + ", quantityStation=" + quantityStation + ")";
    }
}
