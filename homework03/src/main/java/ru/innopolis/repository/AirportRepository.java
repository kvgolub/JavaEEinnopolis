package ru.innopolis.repository;

import ru.innopolis.entity.Airport;

import java.util.List;


public interface AirportRepository {
    List<Airport> findAll();
    void insert(String airport_code, Object airport_name, Object city, String coordinates, String timezone);
    void update(String timezone, String airport_code);
    void delete(String airport_code);
    String findAirport(String airport_en_name);
}
