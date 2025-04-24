package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Driver;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.driver
                SET deleted = true
                WHERE id = :id
            """)
    Integer deleteByIdAsSoft(Long id);

    @Modifying
    @Query(nativeQuery = true, value = """
                UPDATE public.driver
                SET deleted = true
            """)
    Integer deleteAllAsSoft();

}
