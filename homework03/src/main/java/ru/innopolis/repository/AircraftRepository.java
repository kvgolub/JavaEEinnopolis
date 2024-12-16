package ru.innopolis.repository;

import ru.innopolis.entity.Aircraft;

import java.util.List;


public interface AircraftRepository {
    List<Aircraft> findAll();
    void insert(String aircraft_code, Object model, Integer range);
    void update(Integer range, String aircraft_code);
    void delete(String aircraft_code);
    String findAircraft(String aircraft_en_name);
}
