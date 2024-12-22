package ru.innopolis.repository;

import ru.innopolis.model.Bus;
import java.util.List;


public interface BusRepository {
    List<Bus> findLargePassengerCapacity(Integer passengerCapacity);
    List<Bus> findMostExpensive();
    void updateBusType(String nameType, String model);
}
