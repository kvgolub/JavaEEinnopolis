package ru.innopolis.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BusStop {
    private Integer id;
    private String nameStop;
}
