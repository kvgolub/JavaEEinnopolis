package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * <h6>Класс для таблицы Водители.</h6>
 *
 * <p>
 *     Описание свойств класса:
 *     <br>{@code id} - ID записи водителя
 *     <br>{@code surname} - Фамилия водителя
 *     <br>{@code name} - Имя водителя
 *     <br>{@code age} - Возраст водителя
 * </p>
 */
@Entity
@Table(name = "driver", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

}
