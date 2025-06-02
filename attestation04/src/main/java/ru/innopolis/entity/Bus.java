package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Автобус</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "bus", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bus {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Тип
     */
    @Column(name = "type")
    private Long type;

    /**
     * Модель
     */
    @Column(name = "model")
    private String model;

    /**
     * Регистрационный номер
     */
    @Column(name = "reg_number")
    private String regNumber;

    /**
     * Пассажировместимость
     */
    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

    /**
     * Цена
     */
    @Column(name = "price")
    private Double price;
}
