package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Водитель</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "driver", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Фамилия
     */
    @Column(name = "surname")
    private String surname;

    /**
     * Имя
     */
    @Column(name = "name")
    private String name;

    /**
     * Возраст
     */
    @Column(name = "age")
    private Integer age;
}
