package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Bus;
import ru.innopolis.repository.BusRepository;
import java.util.List;


public class BusRepositoryImpl implements BusRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_LARGE_PASSENGER_CAPACITY = """
                SELECT
                    *
                FROM bus
                WHERE passengerCapacity >= ?
            """;
    private static final String FIND_MOST_EXPENSIVE = """
                SELECT
                	*
                FROM bus
                WHERE price = (SELECT max(price) FROM bus)
            """;
    private static final String UPDATE_BUSTYPE = """
                WITH cityBus AS (
                	SELECT id FROM busType WHERE nameType = ?
                )
                UPDATE bus
                SET "type" = (SELECT * FROM cityBus)
                WHERE model = ?
            """;

    @Override
    public List<Bus> findLargePassengerCapacity(Integer passengerCapacity) {
        return jdbcTemplate.query(FIND_LARGE_PASSENGER_CAPACITY, busRowMapper, passengerCapacity);
    }

    @Override
    public List<Bus> findMostExpensive() {
        return jdbcTemplate.query(FIND_MOST_EXPENSIVE, busRowMapper);
    }

    @Override
    public void updateBusType(String nameType, String model) {
        jdbcTemplate.update(UPDATE_BUSTYPE, nameType, model);
    }

    private static final RowMapper<Bus> busRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        Integer type = row.getInt("type");
        String model = row.getString("model");
        String regNumber = row.getString("regNumber");
        Integer passengerCapacity = row.getInt("passengerCapacity");
        Float price = row.getFloat("price");

        return new Bus(id, type, model, regNumber, passengerCapacity, price);
    };
}

