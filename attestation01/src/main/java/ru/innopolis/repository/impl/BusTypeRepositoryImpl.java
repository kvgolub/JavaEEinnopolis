package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.repository.BusTypeRepository;

public class BusTypeRepositoryImpl implements BusTypeRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
    private static final String UPDATE_AND_DELETE_BUS_TYPE = """
                WITH updateTypeBus AS (
                	UPDATE bus
                	SET "type" = (SELECT id FROM busType WHERE nameType = ?)
                	WHERE "type" = (SELECT id FROM busType WHERE nameType = ?)
                )
                DELETE FROM busType
                WHERE nameType = ?
            """;

    @Override
    public void updateAndDeleteBusType(String nameTypeOld, String nameTypeNew, String nameTypeDelete) {
        jdbcTemplate.update(UPDATE_AND_DELETE_BUS_TYPE, nameTypeOld, nameTypeNew, nameTypeDelete);
    }
}

