package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.User;


/**
 * Слой Репозитория
 *
 * <p>Доступ к Базе данных через JPA - таблица Пользователь</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
