package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.repository.BusStopRepository;
import java.util.List;

public class BusStopRepositoryImpl implements BusStopRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String FIND_QUANTITY_BUS_STOP = """
                SELECT
                    count(*)
                FROM busStop
            """;

    @Override
    public List<Integer> findQuantityBusStop() {
        return jdbcTemplate.query(FIND_QUANTITY_BUS_STOP, busStopRowMapper);
    }

    private static final RowMapper<Integer> busStopRowMapper = (row, rowNumber) -> (Integer) row.getInt("count");
}
