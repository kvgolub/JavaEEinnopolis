package ru.innopolis.utils;

import ru.innopolis.dto.bus.BusResponse;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.entity.Schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ScheduleVariables {

    // create
    public static final Schedule create = new Schedule(13L, LocalDateTime.of(2025,1,15,13,0,0), 8L, 2L, 4L);
    public static final ScheduleRequest createRequest = new ScheduleRequest(LocalDateTime.of(2025,1,15,13,0,0), 8L, 2L, 4L);
    public static final ScheduleResponse createResponse = new ScheduleResponse(13L, LocalDateTime.of(2025,1,15,13,0,0), 8L, 2L, 4L);
    public static final String createRequestBody = """
                            {
                            	"routeDate": "2025-01-15T13:00:00",
                             	"routeId": 8,
                             	"busId": 2,
                             	"driverId": 4
                            }
                        """;

    // find
    public static final Schedule find = new Schedule(1L, LocalDateTime.of(2024,12,15, 10, 0, 0), 1L, 6L, 2L);
    public static final ScheduleResponse findResponse = new ScheduleResponse(1L, LocalDateTime.of(2024,12,15, 10, 0, 0), 1L, 6L, 2L);
    public static final String findResponseJson = """
                            {
                            	"id": 1,
                            	"routeDate": "2024-12-15T10:00:00",
                             	"routeId": 1,
                             	"busId": 6,
                             	"driverId": 2
                            }
                        """;

    // findAll
    public static final List<Schedule> findAll = List.of(
            new Schedule(1L, LocalDateTime.of(2024,12,15,10,0,0), 1L, 6L, 2L),
            new Schedule(2L, LocalDateTime.of(2024,12,15,12,0,0), 2L, 5L, 1L)
    );
    public static final List<ScheduleResponse> findAllResponse = List.of(
            new ScheduleResponse(1L, LocalDateTime.of(2024,12,15,10,0,0), 1L, 6L, 2L),
            new ScheduleResponse(2L, LocalDateTime.of(2024,12,15,12,0,0), 2L, 5L, 1L)
    );
    public static final List<ScheduleResponse> findAllResponseEmpty = new ArrayList<>();

    // update
    public static final Schedule update = new Schedule(1L, LocalDateTime.of(2024,12,15, 11,0,0), 9L, 4L, 2L);
    public static final ScheduleRequest updateRequest = new ScheduleRequest(LocalDateTime.of(2024,12,15, 11,0,0), 9L, 4L, 2L);
    public static final ScheduleResponse updateResponse = new ScheduleResponse(1L, LocalDateTime.of(2024,12,15, 11,0,0), 9L, 4L, 2L);
    public static final String updateRequestBody = """
                            {
                                "routeDate": "2024-12-15T11:00:00",
                             	"routeId": 9,
                             	"busId": 4,
                             	"driverId": 2
                            }
                        """;
    public static final String updateResponseJson = """
                            {
                                "id": 1,
                                "routeDate": "2024-12-15T11:00:00",
                             	"routeId": 9,
                             	"busId": 4,
                             	"driverId": 2
                            }
                        """;
}
