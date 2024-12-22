package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.entity.Aircraft;
import ru.innopolis.repository.AircraftRepository;

import java.util.List;


public class AircraftRepositoryImpl implements AircraftRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_ALL_AIRCRAFT = "SELECT * FROM aircrafts_data";
    private static final String INSERT_AIRCRAFT = "INSERT INTO aircrafts_data(aircraft_code, model, range) VALUES (?, ?::jsonb, ?)";
    private static final String UPDATE_AIRCRAFT = "UPDATE aircrafts_data SET range = ? WHERE aircraft_code = ?";
    private static final String DELETE_AIRCRAFT = "DELETE FROM aircrafts_data WHERE aircraft_code = ?";
    private static final String FIND_AIRCRAFT = "SELECT * FROM aircrafts_data WHERE (model ->> 'en') = ?";

    @Override
    public List<Aircraft> findAll() {
        return jdbcTemplate.query(FIND_ALL_AIRCRAFT, aircraftRowMapper);
    }

    @Override
    public void insert(String aircraft_code, Object model, Integer range) {
        jdbcTemplate.update(INSERT_AIRCRAFT, aircraft_code, model, range);
    }

    @Override
    public void update(Integer range, String aircraft_code) {
        jdbcTemplate.update(UPDATE_AIRCRAFT, range, aircraft_code);
    }

    @Override
    public void delete(String aircraft_code) {
        jdbcTemplate.update(DELETE_AIRCRAFT, aircraft_code);
    }

    @Override
    public String findAircraft(String aircraft_en_name) {
        return jdbcTemplate.query(FIND_AIRCRAFT, aircraftRowMapper, aircraft_en_name).get(0).toString();
    }

    private static final RowMapper<Aircraft> aircraftRowMapper = (row, rowNumber) -> {
        String aircraft_code = row.getString("aircraft_code");
        Object model = row.getObject("model");
        Integer range = row.getInt("range");

        return new Aircraft(aircraft_code, model, range);
    };
}
