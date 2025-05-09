package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записей о задачах</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "tasks", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    /**
     * Идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Наименование задачи
     */
    @Column(name = "name")
    private String name;

    /**
     * Описание задачи
     */
    @Column(name = "description")
    private String description;

    /**
     * Дата создания задачи
     */
    @Column(name = "created_at")
    private LocalDate createdAt;

}
