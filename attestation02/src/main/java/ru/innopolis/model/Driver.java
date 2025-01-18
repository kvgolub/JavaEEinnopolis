package ru.innopolis.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Driver {
    private Integer id;
    private String surname;
    private String name;
    private Integer age;
}
