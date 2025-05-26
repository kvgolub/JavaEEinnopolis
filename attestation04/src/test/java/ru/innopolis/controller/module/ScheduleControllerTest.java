package ru.innopolis.controller.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.ScheduleControllerImpl;
import ru.innopolis.dto.schedule.ScheduleRequest;
import ru.innopolis.service.impl.ScheduleServiceImpl;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.BusVariables.updateRequestBody;
import static ru.innopolis.utils.ScheduleVariables.*;


@WebMvcTest(controllers = {ScheduleControllerImpl.class})
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ScheduleServiceImpl scheduleService;


    @WithMockUser(authorities={"USER"})
    @Test
    void createScheduleControllerTest() throws Exception {
        Mockito.when(scheduleService.createSchedule(Mockito.any(ScheduleRequest.class))).thenReturn(createResponse);

        mvc.perform(post("/api/v1/bus_station/schedule")
                        .contentType("application/json")
                        .content(createRequestBody)
                        .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(13));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdScheduleControllerTest() {
        Mockito.when(scheduleService.findByIdSchedule(1L)).thenReturn(Optional.of(findResponse));
        Mockito.when(scheduleService.findByIdSchedule(100L)).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/schedule/1"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/schedule/100"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllSchedulesControllerTest() throws Exception {
        Mockito.when(scheduleService.findAllSchedules()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/schedule/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllSchedulesReturnEmptyControllerTest() throws Exception {
        Mockito.when(scheduleService.findAllSchedules()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/schedule/list"))
                .andExpect(status().isNoContent());
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void updateScheduleControllerTest() {
        Mockito.when(scheduleService.updateSchedule(Mockito.eq(1L), Mockito.any(ScheduleRequest.class))).thenReturn(Optional.of(updateResponse));
        Mockito.when(scheduleService.updateSchedule(Mockito.eq(100L), Mockito.any(ScheduleRequest.class))).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(put("/api/v1/bus_station/schedule/1")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isOk())
                        .andExpect(content().json(updateResponseJson)),
                () ->   mvc.perform(put("/api/v1/bus_station/schedule/100")
                                .contentType("application/json")
                                .content(updateRequestBody)
                                .with(csrf())
                        )
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteByIdScheduleControllerTest() {
        Mockito.when(scheduleService.deleteByIdSchedule(1L)).thenReturn(true);
        Mockito.when(scheduleService.deleteByIdSchedule(100L)).thenReturn(false);

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(delete("/api/v1/bus_station/schedule/1").with(csrf()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").value(true)),
                () -> mvc.perform(delete("/api/v1/bus_station/schedule/100").with(csrf()))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllSchedulesControllerTest() throws Exception {
        Mockito.when(scheduleService.deleteAllSchedules()).thenReturn(11);

        mvc.perform(delete("/api/v1/bus_station/schedule/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(11));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void deleteAllSchedulesReturnEmptyControllerTest() throws Exception {
        Mockito.when(scheduleService.deleteAllSchedules()).thenReturn(0);

        mvc.perform(delete("/api/v1/bus_station/schedule/all").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
