package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Schedule;
import ru.innopolis.model.Selection;
import ru.innopolis.repository.ScheduleRepository;

import java.util.Date;
import java.util.List;


public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO schedule (routeDate, route_id, bus_id, driver_id)
                VALUES (?::timestamp, ?, ?, ?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO schedule (id, routeDate, route_id, bus_id, driver_id)
                VALUES (?, ?::timestamp, ?, ?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM schedule
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM schedule
            """;
    private static final String UPDATE = """
                UPDATE schedule
                SET routeDate = ?::timestamp,
                    route_id = ?,
                    bus_id = ?,
                    driver_id = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM schedule
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM schedule;
            """;


    //Дополнительные запросы
    private static final String FIND_ALL_SCHEDULE_DETAIL = """
                SELECT
                	s.routeDate,
                	bs.nameStop AS nameStopStart,
                	bs2.nameStop AS nameStopEnd,
                	r.passengerFlow,
                	b.model,
                	bt.nameType,
                	b.passengerCapacity,
                	concat(d."name", ' ', d.surname) AS driverNs
                FROM schedule s
                INNER JOIN route r ON r.id = s.route_id
                INNER JOIN busStop bs ON bs.id = r.startStation
                INNER JOIN busStop bs2 ON bs2.id = r.endStation
                INNER JOIN bus b ON b.id = s.bus_id
                INNER JOIN busType bt ON bt.id = b."type"
                INNER JOIN driver d ON d.id = s.driver_id
            """;

    @Override
    public void create(String routeDate, Integer route_id, Integer bus_id, Integer driver_id) {
        jdbcTemplate.update(CREATE, routeDate, route_id, bus_id, driver_id);
    }

    @Override
    public void createWithId(Integer id, String routeDate, Integer route_id, Integer bus_id, Integer driver_id) {
        jdbcTemplate.update(CREATE_WITH_ID, id, routeDate, route_id, bus_id, driver_id);
    }

    @Override
    public List<Schedule> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, scheduleRowMapper, id);
    }

    @Override
    public List<Schedule> findAll() {
        return jdbcTemplate.query(FIND_ALL, scheduleRowMapper);
    }

    @Override
    public Integer update(Object id, Object routeDate, Object route_id, Object bus_id, Object driver_id) {
        return jdbcTemplate.update(UPDATE, routeDate, route_id, bus_id, driver_id, id);
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
    public List<String> findAllScheduleDetail() {
        return jdbcTemplate.query(FIND_ALL_SCHEDULE_DETAIL, scheduleDetailRowMapper);
    }

    private static final RowMapper<Schedule> scheduleRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        Date routeDate = row.getDate("routeDate");
        Integer route_id = row.getInt("route_id");
        Integer bus_id = row.getInt("bus_id");
        Integer driver_id = row.getInt("driver_id");

        return new Schedule(id, routeDate, route_id, bus_id, driver_id);
    };

    private static final RowMapper<String> scheduleDetailRowMapper = (row, rowNumber) -> {
        Date routeDate = row.getDate("routeDate");
        String nameStopStart = row.getString("nameStopStart");
        String nameStopEnd = row.getString("nameStopEnd");
        Integer passengerFlow = row.getInt("passengerFlow");
        String model = row.getString("model");
        String nameType = row.getString("nameType");
        Integer passengerCapacity = row.getInt("passengerCapacity");
        String driverNs = row.getString("driverNs");

        return new Selection().getAllScheduleDetail(routeDate, nameStopStart, nameStopEnd, passengerFlow, model, nameType, passengerCapacity, driverNs);
    };
}
