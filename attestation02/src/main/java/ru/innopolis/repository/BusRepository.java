package ru.innopolis.repository;

import ru.innopolis.model.Bus;
import java.util.List;


/**
 * Интерфейс для таблицы Автобусы
 */
public interface BusRepository {
    void create(Integer type, String model, String regNumber, Integer passengerCapacity, Double price);
    void createWithId(Integer id, Integer type, String model, String regNumber, Integer passengerCapacity, Double price);
    List<Bus> findById(Integer id);
    List<Bus> findAll();
    Integer update(Object id, Object type, Object model, Object regNumber, Object passengerCapacity, Object price);
    Integer deleteByID(Integer id);
    void deleteAll();

    List<Bus> findLargePassengerCapacity(Integer passengerCapacity);
    List<Bus> findMostExpensive();
}
