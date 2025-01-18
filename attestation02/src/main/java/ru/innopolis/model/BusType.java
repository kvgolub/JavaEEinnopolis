package ru.innopolis.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BusType {
    private Integer id;
    private String nameType;
}
