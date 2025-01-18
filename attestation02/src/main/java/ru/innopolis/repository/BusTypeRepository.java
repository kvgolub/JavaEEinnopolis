package ru.innopolis.repository;

import ru.innopolis.model.BusStop;
import ru.innopolis.model.BusType;

import java.util.List;

public interface BusTypeRepository {
    void create(String nameType);
    void createWithId(Integer id, String nameType);
    List<BusType> findById(Integer id);
    List<BusType> findAll();
    Integer update(Object id, Object nameType);
    Integer deleteByID(Integer id);
    void deleteAll();

    void updateAndDeleteBusType(String nameTypeOld, String nameTypeNew, String nameTypeDelete);
}
