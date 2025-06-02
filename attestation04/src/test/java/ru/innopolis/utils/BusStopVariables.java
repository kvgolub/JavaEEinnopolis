package ru.innopolis.utils;

import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.busStop.BusStopRequest;
import ru.innopolis.dto.busStop.BusStopResponse;
import ru.innopolis.entity.BusStop;

import java.util.ArrayList;
import java.util.List;


public class BusStopVariables {

    // create
    public static final BusStop create = new BusStop(11L, "Завод Черный экран");
    public static final BusStopRequest createRequest = new BusStopRequest("Завод Черный экран");
    public static final BusStopResponse createResponse = new BusStopResponse(11L, "Завод Черный экран");
    public static final String createRequestBody = """
                            {
                            	"nameStop": "Завод Черный экран"
                            }
                        """;

    // find
    public static final BusStop find = new BusStop(1L, "Аэропорт");
    public static final BusStopResponse findResponse = new BusStopResponse(1L, "Аэропорт");
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"nameStop": "Аэропорт"
                            }
                        """;

    // findAll
    public static final List<BusStop> findAll = List.of(
            new BusStop(1L, "Аэропорт"),
            new BusStop(2L, "ул.Парковая")
    );
    public static final List<BusStopResponse> findAllResponse = List.of(
            new BusStopResponse(1L, "Аэропорт"),
            new BusStopResponse(2L, "ул.Парковая")
    );
    public static final List<BusStopResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final BusStop update = new BusStop(1L, "ул.Вишнёвая");
    public static final BusStopRequest updateRequest = new BusStopRequest("ул.Вишнёвая");
    public static final BusStopResponse updateResponse = new BusStopResponse(1L, "ул.Вишнёвая");
    public static final String updateRequestBody = """
                            {
                                "nameStop": "ул.Вишнёвая"
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "nameStop": "ул.Вишнёвая"
                            }
                        """;
}
