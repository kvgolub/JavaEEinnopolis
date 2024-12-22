package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.Airport;
import ru.innopolis.repository.AirportRepository;

import java.util.List;


public class AirportRepositoryImpl implements AirportRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_ALL_AIRPORT = "SELECT * FROM airports_data";
    private static final String INSERT_AIRPORT = "INSERT INTO airports_data(airport_code, airport_name, city, coordinates, timezone) VALUES (?, ?::jsonb, ?::jsonb, ?::point, ?)";
    private static final String UPDATE_AIRPORT = "UPDATE airports_data SET timezone = ? WHERE airport_code = ?";
    private static final String DELETE_AIRPORT = "DELETE FROM airports_data WHERE airport_code = ?";
    private static final String FIND_AIRPORT = "SELECT * FROM airports_data WHERE (airport_name ->> 'en') = ?";

    @Override
    public List<Airport> findAll() {
        return jdbcTemplate.query(FIND_ALL_AIRPORT, airportRowMapper);
    }

    @Override
    public void insert(String airport_code, Object airport_name, Object city, String coordinates, String timezone) {
        jdbcTemplate.update(INSERT_AIRPORT, airport_code, airport_name, city, coordinates, timezone);
    }

    @Override
    public void update(String timezone, String airport_code) {
        jdbcTemplate.update(UPDATE_AIRPORT, timezone, airport_code);
    }

    @Override
    public void delete(String airport_code) {
        jdbcTemplate.update(DELETE_AIRPORT, airport_code);
    }

    @Override
    public String findAirport(String airport_en_name) {
        return jdbcTemplate.query(FIND_AIRPORT, airportRowMapper, airport_en_name).get(0).toString();
    }

    private static final RowMapper<Airport> airportRowMapper = (row, rowNumber) -> {
        String airport_code = row.getString("airport_code");
        Object airport_name = row.getObject("airport_name");
        Object city = row.getObject("city");
        String coordinates = row.getString("coordinates");
        String timezone = row.getString("timezone");

        return new Airport(airport_code, airport_name, city, coordinates, timezone);
    };
}
