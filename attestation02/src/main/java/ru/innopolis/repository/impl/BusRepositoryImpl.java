package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Bus;
import ru.innopolis.repository.BusRepository;
import java.util.List;


public class BusRepositoryImpl implements BusRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO bus (type, model, regNumber, passengerCapacity, price)
                VALUES (?, ?, ?, ?, ?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO bus (id, type, model, regNumber, passengerCapacity, price)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM bus
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM bus
            """;
    private static final String UPDATE = """
                UPDATE bus
                SET type = ?,
                    model = ?,
                    regNumber = ?,
                    passengerCapacity = ?,
                    price = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM bus
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM bus;
            """;

    //Дополнительные запросы
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


    @Override
    public void create(Integer type, String model, String regNumber, Integer passengerCapacity, Double price) {
        jdbcTemplate.update(CREATE, type, model, regNumber, passengerCapacity, price);
    }

    @Override
    public void createWithId(Integer id, Integer type, String model, String regNumber, Integer passengerCapacity, Double price) {
        jdbcTemplate.update(CREATE_WITH_ID, id, type, model, regNumber, passengerCapacity, price);
    }

    @Override
    public List<Bus> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, busRowMapper, id);
    }

    @Override
    public List<Bus> findAll() {
        return jdbcTemplate.query(FIND_ALL, busRowMapper);
    }

    @Override
    public Integer update(Object id, Object type, Object model, Object regNumber, Object passengerCapacity, Object price) {
        return jdbcTemplate.update(UPDATE, type, model, regNumber, passengerCapacity, price, id);
    }

    @Override
    public Integer deleteByID(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }

    //Дополнительные запросы
    @Override
    public List<Bus> findLargePassengerCapacity(Integer passengerCapacity) {
        return jdbcTemplate.query(FIND_LARGE_PASSENGER_CAPACITY, busRowMapper, passengerCapacity);
    }

    @Override
    public List<Bus> findMostExpensive() {
        return jdbcTemplate.query(FIND_MOST_EXPENSIVE, busRowMapper);
    }


    private static final RowMapper<Bus> busRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        Integer type = row.getInt("type");
        String model = row.getString("model");
        String regNumber = row.getString("regNumber");
        Integer passengerCapacity = row.getInt("passengerCapacity");
        Double price = row.getDouble("price");

        return new Bus(id, type, model, regNumber, passengerCapacity, price);
    };
}
