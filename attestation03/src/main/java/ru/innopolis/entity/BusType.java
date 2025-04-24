package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * <h6>Класс для таблицы Тип автобуса.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи тип автобуса
 *     <br>{@code nameStop} - Наименование типа автобуса
 * </p>
 */
@Entity
@Table(name = "bus_type", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_type")
    private String nameType;

}
