package ru.innopolis.utils;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;
import ru.innopolis.entity.Route;

import java.util.ArrayList;
import java.util.List;


public class RouteVariables {

    // create
    public static final Route create = new Route(11L, "1000", 2L, 7L,4,650);
    public static final RouteRequest createRequest = new RouteRequest("1000", 2L, 7L,4,650);
    public static final RouteResponse createResponse = new RouteResponse(11L, "1000", 2L, 7L,4,650);
    public static final String createRequestBody = """
                            {
                            	"number": "1000",
                             	"startStation": 2,
                             	"endStation": 7,
                             	"quantityStation": 4,
                             	"passengerFlow": 650
                            }
                        """;

    // find
    public static final Route find = new Route(1L, "1A", 1L, 5L, 10, 500);
    public static final RouteResponse findResponse = new RouteResponse(1L, "1A", 1L, 5L, 10, 500);
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"number": "1A",
                             	"startStation": 1,
                             	"endStation": 5,
                             	"quantityStation": 10,
                             	"passengerFlow": 500
                            }
                        """;

    // findAll
    public static final List<Route> findAll = List.of(
            new Route(1L, "1А", 1L, 5L,10,500),
            new Route(2L, "15", 2L, 6L,20,1000)
    );
    public static final List<RouteResponse> findAllResponse = List.of(
            new RouteResponse(1L, "1А", 1L, 5L,10,500),
            new RouteResponse(2L, "15", 2L, 6L,20,1000)
    );
    public static final List<RouteResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final Route update = new Route(1L, "3", 6L, 2L,11,280);
    public static final RouteRequest updateRequest = new RouteRequest("3", 6L, 2L,11,280);
    public static final RouteResponse updateResponse = new RouteResponse(1L, "3", 6L, 2L,11,280);
    public static final String updateRequestBody = """
                            {
                                "number": "3",
                             	"startStation": 6,
                             	"endStation": 2,
                             	"quantityStation": 11,
                             	"passengerFlow": 280
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "number": "3",
                             	"startStation": 6,
                             	"endStation": 2,
                             	"quantityStation": 11,
                             	"passengerFlow": 280
                            }
                        """;

    // uploadFile
    public static final MockMultipartFile uploadFile = new MockMultipartFile(
            "file",
            "routesRequest.json",
            "application/json",
            """
                [
                    {
                        "number": "2000Э",
                        "startStation": 2,
                        "endStation": 8,
                        "quantityStation": 22,
                        "passengerFlow": 850
                    }
                ]
            """.getBytes()
    );
    public static final MockMultipartFile uploadFileNull = new MockMultipartFile(
            "file",
            "routesRequest.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "".getBytes()
    );
}
