package ru.innopolis.utils;

import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.busType.BusTypeRequest;
import ru.innopolis.dto.busType.BusTypeResponse;
import ru.innopolis.entity.BusType;

import java.util.ArrayList;
import java.util.List;


public class BusTypeVariables {

    // create
    public static final BusType create = new BusType(8L, "амфибия");
    public static final BusTypeRequest createRequest = new BusTypeRequest("амфибия");
    public static final BusTypeResponse createResponse = new BusTypeResponse(8L, "амфибия");
    public static final String createRequestBody = """
                            {
                            	"nameType": "амфибия"
                            }
                        """;

    // find
    public static final BusType find = new BusType(1L, "микроавтобус");
    public static final BusTypeResponse findResponse = new BusTypeResponse(1L, "микроавтобус");
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"nameType": "микроавтобус"
                            }
                        """;

    // findAll
    public static final List<BusType> findAll = List.of(
            new BusType(1L, "микроавтобус"),
            new BusType(2L, "городской")
    );
    public static final List<BusTypeResponse> findAllResponse = List.of(
            new BusTypeResponse(1L, "микроавтобус"),
            new BusTypeResponse(2L, "городской")
    );
    public static final List<BusTypeResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final BusType update = new BusType(1L, "полярный");
    public static final BusTypeRequest updateRequest = new BusTypeRequest("полярный");
    public static final BusTypeResponse updateResponse = new BusTypeResponse(1L, "полярный");
    public static final String updateRequestBody = """
                            {
                                "nameType": "полярный"
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "nameType": "полярный"
                            }
                        """;
}
