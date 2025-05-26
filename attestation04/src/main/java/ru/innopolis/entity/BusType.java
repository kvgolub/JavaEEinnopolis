package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.*;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Тип автобуса</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "bus_type", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusType {

    /**
     * Идентификационный номер записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Наименование типа автобуса
     */
    @Column(name = "name_type")
    private String nameType;
}
