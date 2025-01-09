package ru.innopolis.repository;

public interface BusTypeRepository {
    void updateAndDeleteBusType(String nameTypeOld, String nameTypeNew, String nameTypeDelete);
}

