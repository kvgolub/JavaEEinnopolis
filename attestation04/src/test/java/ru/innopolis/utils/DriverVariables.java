package ru.innopolis.utils;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.driver.DriverRequest;
import ru.innopolis.dto.driver.DriverResponse;
import ru.innopolis.entity.Driver;

import java.util.ArrayList;
import java.util.List;


public class DriverVariables {

    // create
    public static final Driver create = new Driver(7L, "Федоров", "Федор", 55);
    public static final DriverRequest createRequest = new DriverRequest("Федоров", "Федор", 55);
    public static final DriverResponse createResponse = new DriverResponse(7L, "Федоров", "Федор", 55);
    public static final String createRequestBody = """
                            {
                            	"surname": "Федоров",
                                "name": "Федор",
                                "age": 55
                            }
                        """;

    // find
    public static final Driver find = new Driver(1L, "Иванов", "Иван", 50);
    public static final DriverResponse findResponse = new DriverResponse(1L, "Иванов", "Иван", 50);
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"surname": "Иванов",
                                "name": "Иван",
                                "age": 50
                            }
                        """;

    // findAll
    public static final List<Driver> findAll = List.of(
            new Driver(1L, "Иванов", "Иван", 50),
            new Driver(2L, "Петров", "Петр", 35)
    );
    public static final List<DriverResponse> findAllResponse = List.of(
            new DriverResponse(1L, "Иванов", "Иван", 50),
            new DriverResponse(2L, "Петров", "Петр", 35)
    );
    public static final List<DriverResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final Driver update = new Driver(1L, "Николаев", "Николай", 22);
    public static final DriverRequest updateRequest = new DriverRequest("Николаев", "Николай", 22);
    public static final DriverResponse updateResponse = new DriverResponse(1L, "Николаев", "Николай", 22);
    public static final String updateRequestBody = """
                            {
                                "surname": "Николаев",
                                "name": "Николай",
                                "age": 22
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "surname": "Николаев",
                                "name": "Николай",
                                "age": 22
                            }
                        """;

    // uploadFile
    public static final MockMultipartFile uploadFile = new MockMultipartFile(
            "file",
            "driversRequest.json",
            "application/json",
            """
                [
                    {
                        "surname": "Сергеев",
                        "name": "Сергей",
                        "age": 52
                    }
                ]
            """.getBytes()
    );
    public static final MockMultipartFile uploadFileNull = new MockMultipartFile(
            "file",
            "driversRequest.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "".getBytes()
    );
}
