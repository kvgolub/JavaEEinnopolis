package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Route;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Маршрут.
 *     Интерфейс Репозитория Маршрут содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Маршрут по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Маршрут (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /**
     * Метод "логически удалить запись Маршрут по ID номеру (soft delete)"
     * @param id содержит ID номер записи Маршрут
     * @return возвращается подтверждение об удалении записи Маршрут в виде числа
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.route
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    /**
     * Метод "логически удалить все записи Маршрут (soft delete)"
     * @return возвращается подтверждение в виде количества удаленных записей Маршрут
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.route
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
