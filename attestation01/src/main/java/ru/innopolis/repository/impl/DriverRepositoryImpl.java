package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Selection;
import ru.innopolis.repository.DriverRepository;

import java.util.List;

public class DriverRepositoryImpl implements DriverRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_DRIVER_ON_BUS = """
                SELECT
                	d.surname,
                	b.model,
                	b.regNumber
                FROM driver d
                INNER JOIN schedule s ON s.driver_id = d.id
                INNER JOIN bus b ON b.id = s.bus_id
                WHERE d.surname = ?
            """;

    @Override
    public List<String> findDriverOnBus(String surname) {
        return jdbcTemplate.query(FIND_DRIVER_ON_BUS, driverRowMapper, surname);
    }

    private static final RowMapper<String> driverRowMapper = (row, rowNumber) -> {
        String surname = row.getString("surname");
        String model = row.getString("model");
        String regNumber = row.getString("regNumber");

        return new Selection().getSelectionDriverBus(surname, model, regNumber);
    };
}

