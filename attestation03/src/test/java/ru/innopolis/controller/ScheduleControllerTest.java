package ru.innopolis.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.ScheduleControllerImpl;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.dto.schedule.ScheduleResponse;
import ru.innopolis.service.impl.ScheduleServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {ScheduleControllerImpl.class})
public class ScheduleControllerTest {

    @MockitoBean
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private MockMvc mvc;

    public ScheduleControllerTest() throws ParseException {
    }


    @WithMockUser(authorities={"USER"})
    @Test
    void createScheduleControllerTest() throws Exception {
        Mockito.when(scheduleService.createSchedule(Mockito.any(ScheduleRequest.class))).thenReturn(scheduleResponse);

        mvc.perform(post("/api/v1/bus_station/schedule")
                        .contentType("application/json")
                        .content(scheduleRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdScheduleControllerTest() throws Exception {
        Mockito.when(scheduleService.findByIdSchedule(Mockito.any(Long.class))).thenReturn(Optional.of(scheduleResponse));

        mvc.perform(get("/api/v1/bus_station/schedule/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(scheduleResponseJson));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllSchedulesControllerTest() throws Exception {
        Mockito.when(scheduleService.findAllSchedules()).thenReturn(scheduleResponseAll);

        mvc.perform(get("/api/v1/bus_station/schedule/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateScheduleControllerTest() throws Exception {
        Mockito.when(scheduleService.updateSchedule(Mockito.any(Long.class), Mockito.any(ScheduleRequest.class))).thenReturn(Optional.of(scheduleResponseNew));

        mvc.perform(put("/api/v1/bus_station/schedule/1")
                        .contentType("application/json")
                        .content(scheduleRequestNewBody)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().json(scheduleResponseNewJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdScheduleControllerTest() throws Exception {
        Mockito.when(scheduleService.deleteByIdSchedule(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/bus_station/schedule/1").with(csrf()))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllSchedulesControllerTest() throws Exception {
        Mockito.when(scheduleService.deleteAllSchedules()).thenReturn(1);

        mvc.perform(delete("/api/v1/bus_station/schedule/all").with(csrf()))
                .andExpect(status().isOk());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final ScheduleResponse scheduleResponse = new ScheduleResponse(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L);
    private final String scheduleRequestBody = """
                            {
                            	"routeDate": "2024-03-01T10:00:00.000+02:00",
                             	"routeId": 1,
                             	"busId": 6,
                             	"driverId": 2
                            }
                        """;

    // find
    private final String scheduleResponseJson = """
                            {
                            	"id": 1,
                            	"routeDate": "2024-03-01T08:00:00.000+00:00",
                             	"routeId": 1,
                             	"busId": 6,
                             	"driverId": 2
                            }
                        """;

    // findAll
    private final List<ScheduleResponse> scheduleResponseAll = List.of(
            new ScheduleResponse(1L, sdf.parse("2024-03-01T10:00:00.000+02:00"), 1L, 6L, 2L),
            new ScheduleResponse(2L, sdf.parse("2024-03-02T10:00:00.000+02:00"), 2L, 5L, 1L)
    );

    // update
    private final ScheduleResponse scheduleResponseNew = new ScheduleResponse(1L, sdf.parse("2024-03-03T11:00:00.000+02:00"), 5L, 1L, 6L);
    private final String scheduleRequestNewBody = """
                            {
                                "routeDate": "2024-03-03T11:00:00.000+02:00",
                             	"routeId": 5,
                             	"busId": 1,
                             	"driverId": 6
                            }
                        """;
    private final String scheduleResponseNewJson = """
                            {
                                "id": 1,
                                "routeDate": "2024-03-03T09:00:00.000+00:00",
                             	"routeId": 5,
                             	"busId": 1,
                             	"driverId": 6
                            }
                        """;

}
