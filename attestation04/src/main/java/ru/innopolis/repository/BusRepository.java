package ru.innopolis.repository;

import org.springframework.boot.test.autoconfigure.data.redis.AutoConfigureDataRedis;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Bus;


/**
 * Слой Репозитория
 *
 * <p>
 *     Доступ к Базе данных через JPA - таблица Автобус.
 *     Интерфейс Репозитория Автобус содержит 2 дополнительных метода:
 * </p>
 *
 * <p>
 *     {@code deleteByIdAsSoft} - логически удалить запись Автобус по ID номеру (soft delete)
 *     {@code deleteAllAsSoft} - логически удалить все записи Автобус (soft delete)
 * </p>
 *
 * @author K.Golub
 * @version 1.0
 */
@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {


    /**
     * Метод "логически удалить запись Автобус по ID номеру (soft delete)"
     * @param id содержит ID номер записи Автобус
     * @return возвращается подтверждение об удалении записи Автобус в виде числа
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    /**
     * Метод "логически удалить все записи Автобус (soft delete)"
     * @return возвращается подтверждение в виде количества удаленных записей Автобус
     */
    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus
                SET deleted = true
            """)
    Integer deleteAllAsSoft();
}
