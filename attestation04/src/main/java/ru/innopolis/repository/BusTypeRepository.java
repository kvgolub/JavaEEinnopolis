package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.BusType;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Тип автобуса.
 *     Интерфейс Репозитория Тип автобуса содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Тип автобуса по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Тип автобуса (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface BusTypeRepository extends JpaRepository<BusType, Long> {

    /**
     * Метод "логически удалить запись Тип автобуса по ID номеру (soft delete)"
     * @param id содержит ID номер записи Тип автобуса
     * @return возвращается подтверждение об удалении записи Тип автобуса в виде числа
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_type
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    /**
     * Метод "логически удалить все записи Тип автобуса (soft delete)"
     * @return возвращается подтверждение в виде количества удаленных записей Тип автобуса
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_type
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
