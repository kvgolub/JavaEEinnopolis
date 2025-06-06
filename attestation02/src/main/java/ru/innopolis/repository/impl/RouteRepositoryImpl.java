package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.innopolis.config.JdbcTemplateConfig;
import ru.innopolis.model.Route;
import ru.innopolis.model.Selection;
import ru.innopolis.repository.RouteRepository;

import java.util.List;


public class RouteRepositoryImpl implements RouteRepository {
    private final JdbcTemplate jdbcTemplate = JdbcTemplateConfig.jdbcTemplate();

    private static final String CREATE = """
                INSERT INTO route (number, startStation, endStation, quantityStation, passengerFlow)
                VALUES (?, ?, ?, ?, ?)
            """;
    private static final String CREATE_WITH_ID = """
                INSERT INTO route (id, number, startStation, endStation, quantityStation, passengerFlow)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
    private static final String FIND_BY_ID = """
                SELECT
                    *
                FROM route
                WHERE id = ?
            """;
    private static final String FIND_ALL = """
                SELECT
                    *
                FROM route
            """;
    private static final String UPDATE = """
                UPDATE route
                SET number = ?,
                    startStation = ?,
                    endStation = ?,
                    quantityStation = ?,
                    passengerFlow = ?
                WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
                DELETE FROM route
                WHERE id = ?;
            """;
    private static final String DELETE_ALL = """
                DELETE FROM route;
            """;


    //Дополнительные запросы
    private static final String FIND_CONTAIN_BUS_STOP = """
                SELECT
                	bs.nameStop,
                	r."number"
                FROM route r
                INNER JOIN busStop bs ON bs.id = r.startStation OR bs.id = r.endStation
                WHERE bs.nameStop = ?
            """;
    private static final String FIND_LEAST_QUANTITY_STATION = """
                SELECT
                	number,
                	quantityStation
                FROM route
                ORDER BY
                	quantityStation ASC LIMIT 1;
            """;
    private static final String FIND_LARGE_PASSENGER_FLOW_AND_EFFECT_BUS = """
                WITH
                	routeMaxPassengerFlow AS (
                		SELECT
                			*
                		FROM route
                		WHERE passengerFlow = (SELECT max(passengerFlow) FROM route)
                	),
                	busMaxPassengerCapacity AS (
                		SELECT
                			*
                		FROM bus
                		WHERE passengerCapacity = (
                			SELECT
                				max(passengerCapacity)
                			FROM bus b
                			INNER JOIN busType bt ON bt.id = b."type"
                			WHERE bt.nameType != ?
                		)
                	)
                SELECT
                	(SELECT "number" FROM routeMaxPassengerFlow),
                	(SELECT passengerFlow FROM routeMaxPassengerFlow),
                	(SELECT model FROM busMaxPassengerCapacity),
                	(SELECT passengerCapacity FROM busMaxPassengerCapacity),
                	ceiling((SELECT passengerFlow FROM routeMaxPassengerFlow) / (SELECT passengerCapacity FROM busMaxPassengerCapacity)::numeric(15,2)) AS countRoutes
            """;

    @Override
    public void create(String number, Integer startStation, Integer endStation, Integer quantityStation, Integer passengerFlow) {
        jdbcTemplate.update(CREATE, number, startStation, endStation, quantityStation, passengerFlow);
    }

    @Override
    public void createWithId(Integer id, String number, Integer startStation, Integer endStation, Integer quantityStation, Integer passengerFlow) {
        jdbcTemplate.update(CREATE_WITH_ID, id, number, startStation, endStation, quantityStation, passengerFlow);
    }

    @Override
    public List<Route> findById(Integer id) {
        return jdbcTemplate.query(FIND_BY_ID, routeRowMapper, id);
    }

    @Override
    public List<Route> findAll() {
        return jdbcTemplate.query(FIND_ALL, routeRowMapper);
    }

    @Override
    public Integer update(Object id, Object number, Object startStation, Object endStation, Object quantityStation, Object passengerFlow) {
        return jdbcTemplate.update(UPDATE, number, startStation, endStation, quantityStation, passengerFlow, id);
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
    public List<String> findContainBusStop(String nameStop) {
        return jdbcTemplate.query(FIND_CONTAIN_BUS_STOP, containBusStopRowMapper, nameStop);
    }

    @Override
    public List<String> findLeastQuantityStation() {
        return jdbcTemplate.query(FIND_LEAST_QUANTITY_STATION, leastQuantityStationRowMapper);
    }

    @Override
    public List<String> findLargePassengerFlowAndEffectBus(String nameType) {
        return jdbcTemplate.query(FIND_LARGE_PASSENGER_FLOW_AND_EFFECT_BUS, largePassengerFlowAndEffectBusRowMapper, nameType);
    }

    private static final RowMapper<Route> routeRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String number = row.getString("number");
        Integer startStation = row.getInt("startStation");
        Integer endStation = row.getInt("endStation");
        Integer quantityStation = row.getInt("quantityStation");
        Integer passengerFlow = row.getInt("passengerFlow");

        return new Route(id, number, startStation, endStation, quantityStation, passengerFlow);
    };

    private static final RowMapper<String> containBusStopRowMapper = (row, rowNumber) -> {
        String nameStop = row.getString("nameStop");
        String number = row.getString("number");

        return new Selection().getContainBusStop(nameStop, number);
    };

    private static final RowMapper<String> leastQuantityStationRowMapper = (row, rowNumber) -> {
        String number = row.getString("number");
        Integer quantityStation = row.getInt("quantityStation");

        return new Route(number, quantityStation).getLeastQuantityStation();
    };

    private static final RowMapper<String> largePassengerFlowAndEffectBusRowMapper = (row, rowNumber) -> {
        String number = row.getString("number");
        Integer passengerFlow = row.getInt("passengerFlow");
        String model = row.getString("model");
        Integer passengerCapacity = row.getInt("passengerCapacity");
        Float countRoutes = row.getFloat("countRoutes");

        return new Selection().getLargePassengerFlowAndEffectBus(number, passengerFlow, model, passengerCapacity, countRoutes);
    };
}
