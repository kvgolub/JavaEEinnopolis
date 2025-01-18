package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Bus;
import ru.innopolis.model.BusStop;
import ru.innopolis.repository.BusStopRepository;
import java.util.List;

public class BusStopRepositoryImpl implements BusStopRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO busStop (nameStop)
                VALUES (?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO busStop (id, nameStop)
                VALUES (?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM busStop
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM busStop
            """;
    private static final String UPDATE = """
                UPDATE busStop
                SET nameStop = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM busStop
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM busStop;
            """;

    //Дополнительные запросы
    private static final String FIND_QUANTITY_BUS_STOP = """
                SELECT
                    count(*)
                FROM busStop
            """;


    @Override
    public void create(String nameStop) {
        jdbcTemplate.update(CREATE, nameStop);
    }

    @Override
    public void createWithId(Integer id, String nameStop) {
        jdbcTemplate.update(CREATE_WITH_ID, id, nameStop);
    }

    @Override
    public List<BusStop> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, busStopRowMapper, id);
    }

    @Override
    public List<BusStop> findAll() {
        return jdbcTemplate.query(FIND_ALL, busStopRowMapper);
    }

    @Override
    public Integer update(Object id, Object nameStop) {
        return jdbcTemplate.update(UPDATE, nameStop, id);
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
    public List<Integer> findQuantityBusStop() {
        return jdbcTemplate.query(FIND_QUANTITY_BUS_STOP, quantityBusStopRowMapper);
    }

    private static final RowMapper<BusStop> busStopRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String nameStop = row.getString("nameStop");

        return new BusStop(id, nameStop);
    };

    private static final RowMapper<Integer> quantityBusStopRowMapper = (row, rowNumber) -> (Integer) row.getInt("count");
}
