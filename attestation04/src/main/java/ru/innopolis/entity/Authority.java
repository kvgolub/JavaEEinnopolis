package ru.innopolis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Слой объектов Базы данных
 *
 * <p>Запись в базу данных/извлечение из базы данных записи Полномочие роли</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Entity
@Table(name = "authorities", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority {

    /**
     * Имя пользователя
     */
    @Id
    @Column(name = "username")
    private String username;

    /**
     * Полномочие роли
     */
    @Column(name = "authority")
    private String authority;
}
