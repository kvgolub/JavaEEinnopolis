package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Driver;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Водитель.
 *     Интерфейс Репозитория Водитель содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Водитель по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Водитель (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    /**
     * Метод "логически удалить запись Водитель по ID номеру (soft delete)"
     * @param id содержит ID номер записи Водитель
     * @return возвращается подтверждение об удалении записи Водитель в виде числа
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.driver
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    /**
     * Метод "логически удалить все записи Водитель (soft delete)"
     * @return возвращается подтверждение в виде количества удаленных записей Водитель
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.driver
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
