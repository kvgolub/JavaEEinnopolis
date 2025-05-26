package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Schedule;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Расписание.
 *     Интерфейс Репозитория Расписание содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Расписание по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Расписание (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.schedule
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.schedule
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
