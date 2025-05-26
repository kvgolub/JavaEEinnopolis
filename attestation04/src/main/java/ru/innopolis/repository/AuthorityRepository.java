package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Authority;


/**
 * Слой Репозитория
 *
 * <p>Доступ к Базе данных через JPA - таблица Полномочие роли</p>
 *
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
