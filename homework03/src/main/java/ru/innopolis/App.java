package ru.innopolis;

import ru.innopolis.repository.AircraftRepository;
import ru.innopolis.repository.AirportRepository;
import ru.innopolis.repository.FlightRepository;
import ru.innopolis.repository.impl.AircraftRepositoryImpl;
import ru.innopolis.repository.impl.AirportRepositoryImpl;
import ru.innopolis.repository.impl.FlightRepositoryImpl;


public class App {
    private static final AircraftRepository AIRCRAFT_REPOSITORY = new AircraftRepositoryImpl();
    private static final AirportRepository AIRPORT_REPOSITORY = new AirportRepositoryImpl();
    private static final FlightRepository FLIGHT_REPOSITORY = new FlightRepositoryImpl();

    public static void main(String[] args) {
        //CRUD запросы для таблицы Самолеты
        var allAircrafts = AIRCRAFT_REPOSITORY.findAll();
        System.out.println(allAircrafts);

        AIRCRAFT_REPOSITORY.insert("M21", "{\"en\": \"MS-21\", \"ru\": \"МС-21\"}", 2100);
        AIRCRAFT_REPOSITORY.update(2000, "CN1");
        AIRCRAFT_REPOSITORY.delete("M22");

        var aircraft = AIRCRAFT_REPOSITORY.findAircraft("Boeing 737-300");
        System.out.println(aircraft);


        //CRUD запросы для таблицы Аэропорты
        var allAirports= AIRPORT_REPOSITORY.findAll();
        System.out.println(allAirports);

        AIRPORT_REPOSITORY.insert("KG2", "{\"en\": \"Khrabrovo Airpor 2\", \"ru\": \"Храброво-2\"}", "{\"en\": \"Kaliningrad\", \"ru\": \"Калининград\"}", "(20.592599868774414,54.88999938964844)", "Europe/Kaliningrad");
        AIRPORT_REPOSITORY.update("Europe/Moscow", "KUF");
        AIRPORT_REPOSITORY.delete("KG3");

        var airport = AIRPORT_REPOSITORY.findAirport("Kazan International Airport");
        System.out.println(airport);


        //Выборка состоявшихся полетов для определенного Самолета
        var flight = FLIGHT_REPOSITORY.findFligtAircraft("Boeing 737-300", "Arrived");
        System.out.println(flight);
    }
}
