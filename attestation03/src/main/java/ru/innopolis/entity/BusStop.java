package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * <h6>Класс для таблицы Автобусные остановки.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи автобусная остановка
 *     <br>{@code nameStop} - Название остановки
 * </p>
 */
@Entity
@Table(name = "bus_stop", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_stop")
    private String nameStop;

}
