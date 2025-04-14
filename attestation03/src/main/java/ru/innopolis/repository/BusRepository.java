package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Bus;

import java.util.List;


@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus
                SET deleted = true
            """)
    Integer deleteAllAsSoft();

}
