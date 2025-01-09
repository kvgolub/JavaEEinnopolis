package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Selection;
import ru.innopolis.repository.ScheduleRepository;

import java.util.Date;
import java.util.List;

public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();
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
    private static final String UPDATE_BUS_ON_DATE = """
                UPDATE schedule
                SET
                	bus_id = (SELECT id FROM bus WHERE model = ?),
                	driver_id = (SELECT id FROM driver WHERE surname = ?)
                WHERE routeDate = ?::timestamp
            """;
    private static final String DELETE_BUS_FROM_SCHEDULE = """
                DELETE FROM schedule
                WHERE bus_id = (SELECT id FROM bus WHERE model = ?);
            """;

    @Override
    public List<String> findAllScheduleDetail() {
        return jdbcTemplate.query(FIND_ALL_SCHEDULE_DETAIL, scheduleRowMapper);
    }

    @Override
    public void updateBusOnDate(String model, String surname, String routeDate) {
        jdbcTemplate.update(UPDATE_BUS_ON_DATE, model, surname, routeDate);
    }

    @Override
    public void deleteBusFromSchedule(String model) {
        jdbcTemplate.update(DELETE_BUS_FROM_SCHEDULE, model);
    }

    private static final RowMapper<String> scheduleRowMapper = (row, rowNumber) -> {
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

