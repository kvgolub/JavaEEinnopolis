package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Task;


/**
 * Слой Репозитория
 *
 * <p>Доступ к Базе данных через JPA</p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
