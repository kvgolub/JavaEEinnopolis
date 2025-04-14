package ru.innopolis.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "bus", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Long type;

    @Column(name = "model")
    private String model;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

    @Column(name = "price")
    private Double price;

}
