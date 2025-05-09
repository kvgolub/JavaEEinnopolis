package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Task;

import java.time.LocalDate;
import java.util.List;


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

    List<Task> findTasksByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

}
