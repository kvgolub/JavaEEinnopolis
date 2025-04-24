package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.BusStop;


@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_stop
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.bus_stop
                SET deleted = true
            """)
    Integer deleteAllAsSoft();

}
