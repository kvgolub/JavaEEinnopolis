package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Driver;
import ru.innopolis.model.Selection;
import ru.innopolis.repository.DriverRepository;

import java.util.List;


public class DriverRepositoryImpl implements DriverRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO driver (surname, name, age)
                VALUES (?, ?, ?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO driver (id, surname, name, age)
                VALUES (?, ?, ?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM driver
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM driver
            """;
    private static final String UPDATE = """
                UPDATE driver
                SET surname = ?,
                    name = ?,
                    age = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM driver
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM driver;
            """;


    //Дополнительные запросы
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
    public void create(String surname, String name, Integer age) {
        jdbcTemplate.update(CREATE, surname, name, age);
    }

    @Override
    public void createWithId(Integer id, String surname, String name, Integer age) {
        jdbcTemplate.update(CREATE_WITH_ID, id, surname, name, age);
    }

    @Override
    public List<Driver> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, driverRowMapper, id);
    }

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(FIND_ALL, driverRowMapper);
    }

    @Override
    public Integer update(Object id, Object surname, Object name, Object age) {
        return jdbcTemplate.update(UPDATE, surname, name, age, id);
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
    public List<String> findDriverOnBus(String surname) {
        return jdbcTemplate.query(FIND_DRIVER_ON_BUS, driverOnBusRowMapper, surname);
    }

    private static final RowMapper<Driver> driverRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String surname = row.getString("surname");
        String name = row.getString("name");
        Integer age = row.getInt("age");

        return new Driver(id, surname, name, age);
    };

    private static final RowMapper<String> driverOnBusRowMapper = (row, rowNumber) -> {
        String surname = row.getString("surname");
        String model = row.getString("model");
        String regNumber = row.getString("regNumber");

        return new Selection().getSelectionDriverBus(surname, model, regNumber);
    };
}
