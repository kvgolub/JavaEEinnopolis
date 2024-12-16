package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.Flight;
import ru.innopolis.repository.FlightRepository;

import java.util.Date;
import java.util.List;


public class FlightRepositoryImpl implements FlightRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_FLIGHT_AIRCRAFT = """
                SELECT
                    f.*
                FROM flights f
                INNER JOIN aircrafts_data ad ON ad.aircraft_code = f.aircraft_code
                WHERE (ad.model ->> 'en') = ?
                    AND f.status = ?
            """;

    @Override
    public List<Flight> findFligtAircraft(String aircraft_en_name, String status) {
        return jdbcTemplate.query(FIND_FLIGHT_AIRCRAFT, flightRowMapper, aircraft_en_name, status);
    }

    private static final RowMapper<Flight> flightRowMapper = (row, rowNumber) -> {
        Integer flight_id = row.getInt("flight_id");
        String flight_no = row.getString("flight_no");
        Date scheduled_departure = row.getDate("scheduled_departure");
        Date scheduled_arrival = row.getDate("scheduled_arrival");
        String departure_airport = row.getString("departure_airport");
        String arrival_airport = row.getString("arrival_airport");
        String status = row.getString("status");
        String aircraft_code = row.getString("aircraft_code");
        Date actual_departure = row.getDate("actual_departure");
        Date actual_arrival = row.getDate("actual_arrival");

        return new Flight(flight_id, flight_no, scheduled_departure, scheduled_arrival, departure_airport, arrival_airport, status, aircraft_code, actual_departure, actual_arrival);
    };
}
