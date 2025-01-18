package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.BusStop;
import ru.innopolis.model.BusType;
import ru.innopolis.repository.BusTypeRepository;

import java.util.List;


public class BusTypeRepositoryImpl implements BusTypeRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO busType (nameType)
                VALUES (?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO busType (id, nameType)
                VALUES (?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM busType
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM busType
            """;
    private static final String UPDATE = """
                UPDATE busType
                SET nameType = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM busType
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM busType;
            """;

    //Дополнительные запросы
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
    public void create(String nameType) {
        jdbcTemplate.update(CREATE, nameType);
    }

    @Override
    public void createWithId(Integer id, String nameType) {
        jdbcTemplate.update(CREATE_WITH_ID, id, nameType);
    }

    @Override
    public List<BusType> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, busTypeRowMapper, id);
    }

    @Override
    public List<BusType> findAll() {
        return jdbcTemplate.query(FIND_ALL, busTypeRowMapper);
    }

    @Override
    public Integer update(Object id, Object nameType) {
        return jdbcTemplate.update(UPDATE, nameType, id);
    }

    @Override
    public Integer deleteByID(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }

    @Override
    public void updateAndDeleteBusType(String nameTypeOld, String nameTypeNew, String nameTypeDelete) {
        jdbcTemplate.update(UPDATE_AND_DELETE_BUS_TYPE, nameTypeOld, nameTypeNew, nameTypeDelete);
    }

    private static final RowMapper<BusType> busTypeRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String nameType = row.getString("nameType");

        return new BusType(id, nameType);
    };
}
