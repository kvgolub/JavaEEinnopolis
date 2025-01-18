package ru.innopolis.model;

import lombok.*;


/**
 * <h6>Класс для таблицы Автобусы.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи автобуса
 *     <br>{@code type} - Тип автобуса
 *     <br>{@code model} - Марка автобуса
 *     <br>{@code regNumber} - Регистрационный номер автобуса
 *     <br>{@code passengerCapacity} - Пассажировместимость автобуса
 *     <br>{@code price} - Стоимость автобуса
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bus {
    private Integer id;
    private Integer type;
    private String model;
    private String regNumber;
    private Integer passengerCapacity;
    private Double price;
}
