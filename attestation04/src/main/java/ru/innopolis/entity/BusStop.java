package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Автобусная остановка</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "bus_stop", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusStop {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Наименование остановки
     */
    @Column(name = "name_stop")
    private String nameStop;
}
