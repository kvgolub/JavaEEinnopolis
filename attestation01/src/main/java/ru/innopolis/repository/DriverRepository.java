package ru.innopolis.repository;

import java.util.List;

public interface DriverRepository {
    List<String> findDriverOnBus(String surname);
}

