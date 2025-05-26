package ru.innopolis.utils;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.innopolis.dto.bus.BusRequest;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.entity.Bus;

import java.util.ArrayList;
import java.util.List;


public class BusVariables {

    // create
    public static final Bus create = new Bus(11L, 3L, "МАЗ", "е404рр", 60, 2500000.00);
    public static final BusRequest createRequest = new BusRequest(3L, "МАЗ", "е404рр", 60, 2500000.00);
    public static final BusResponse createResponse = new BusResponse(11L, 3L, "МАЗ", "е404рр", 60, 2500000.00);
    public static final String createRequestBody = """
                            {
                            	"type": 3,
                                "model": "МАЗ",
                                "regNumber": "е404рр",
                                "passengerCapacity": 60,
                                "price": 2500000.00
                            }
                        """;

    // find
    public static final Bus find = new Bus(1L, 1L, "Газель", "а001аа", 20, 1000000.00);
    public static final BusResponse findResponse = new BusResponse(1L, 1L, "Газель", "а001аа", 20, 1000000.00);
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"type": 1,
                                "model": "Газель",
                                "regNumber": "а001аа",
                                "passengerCapacity": 20,
                                "price": 1000000.00
                            }
                        """;

    // findAll
    public static final List<Bus> findAll = List.of(
            new Bus(1L, 1L, "Газель", "а001аа", 20, 1000000.00),
            new Bus(2L, 2L, "ЛиАЗ", "в002aa", 50, 1500000.50)
    );
    public static final List<BusResponse> findAllResponse = List.of(
            new BusResponse(1L, 1L, "Газель", "а001аа", 20, 1000000.00),
            new BusResponse(2L, 2L, "ЛиАЗ", "в002aa", 50, 1500000.50)
    );
    public static final List<BusResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final Bus update = new Bus(1L, 1L, "Газель", "р100ом", 30,1500000.00);
    public static final BusRequest updateRequest = new BusRequest(1L, "Газель", "р100ом", 30,1500000.00);
    public static final BusResponse updateResponse = new BusResponse(1L, 1L, "Газель", "р100ом", 30,1500000.00);
    public static final String updateRequestBody = """
                            {
                                "type": 1,
                                "model": "Газель",
                                "regNumber": "р100ом",
                                "passengerCapacity": 30,
                                "price": 1500000.00
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "type": 1,
                                "model": "Газель",
                                "regNumber": "р100ом",
                                "passengerCapacity": 30,
                                "price": 1500000.00
                            }
                        """;

    // uploadFile
    public static final MockMultipartFile uploadFile = new MockMultipartFile(
            "file",
            "busesRequest.json",
            "application/json",
            """
                [
                    {
                        "type": 1,
                        "model": "Автобус-100",
                        "regNumber": "A001AA",
                        "passengerCapacity": 100,
                        "price": 20000000.00
                    }
                ]
            """.getBytes()
    );
    public static final MockMultipartFile uploadFileNull = new MockMultipartFile(
            "file",
            "busesRequest.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "".getBytes()
    );
}
