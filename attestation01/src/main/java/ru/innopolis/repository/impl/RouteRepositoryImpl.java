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
    public List<String> findContainBusStop(String nameStop) {
        return jdbcTemplate.query(FIND_CONTAIN_BUS_STOP, routeRowMapperContainBusStop, nameStop);
    }

    @Override
    public List<String> findLeastQuantityStation() {
        return jdbcTemplate.query(FIND_LEAST_QUANTITY_STATION, routeRowMapperLeastQuantityStation);
    }

    @Override
    public List<String> findLargePassengerFlowAndEffectBus(String nameType) {
        return jdbcTemplate.query(FIND_LARGE_PASSENGER_FLOW_AND_EFFECT_BUS, routeRowMapperLargePassengerFlowAndEffectBus, nameType);
    }

    private static final RowMapper<String> routeRowMapperContainBusStop = (row, rowNumber) -> {
        String nameStop = row.getString("nameStop");
        String number = row.getString("number");

        return new Selection().getContainBusStop(nameStop, number);
    };

    private static final RowMapper<String> routeRowMapperLeastQuantityStation = (row, rowNumber) -> {
        String number = row.getString("number");
        Integer quantityStation = row.getInt("quantityStation");

        return new Route(number, quantityStation).getLeastQuantityStation();
    };

    private static final RowMapper<String> routeRowMapperLargePassengerFlowAndEffectBus = (row, rowNumber) -> {
        String number = row.getString("number");
        Integer passengerFlow = row.getInt("passengerFlow");
        String model = row.getString("model");
        Integer passengerCapacity = row.getInt("passengerCapacity");
        Float countRoutes = row.getFloat("countRoutes");

        return new Selection().getLargePassengerFlowAndEffectBus(number, passengerFlow, model, passengerCapacity, countRoutes);
    };
}

