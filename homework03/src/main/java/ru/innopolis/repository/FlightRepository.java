package ru.innopolis.repository;

import ru.innopolis.entity.Flight;

import java.util.List;

public interface FlightRepository {
    List<Flight> findFligtAircraft(String aircraft_en_name, String status);
}
