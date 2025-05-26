package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.BusStop;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Автобусная остановка.
 *     Интерфейс Репозитория Автобусная остановка содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Автобусная остановка по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Автобусная остановка (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    /**
     * Метод "логически удалить запись Автобусная остановка по ID номеру (soft delete)"
     * @param id содержит ID номер записи Автобусная остановка
     * @return возвращается подтверждение об удалении записи Автобусная остановка в виде числа
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_stop
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    /**
     * Метод "логически удалить все записи Автобусная остановка (soft delete)"
     * @return возвращается подтверждение в виде количества удаленных записей Автобусная остановка
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_stop
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
