package ru.innopolis.repository;

import ru.innopolis.model.Driver;

import java.util.List;


/**
 * Интерфейс для таблицы Водители
 */
public interface DriverRepository {
    void create(String surname, String name, Integer age);
    void createWithId(Integer id, String surname, String name, Integer age);
    List<Driver> findById(Integer id);
    List<Driver> findAll();
    Integer update(Object id, Object surname, Object name, Object age);
    Integer deleteByID(Integer id);
    void deleteAll();

    List<String> findDriverOnBus(String surname);
}
