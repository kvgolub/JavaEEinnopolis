package ru.innopolis.controller.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.UserControllerImpl;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static ru.innopolis.utils.UserVariables.*;


@WebMvcTest(controllers = {UserControllerImpl.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserServiceImpl userService;


    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdUserControllerTest() {
        Mockito.when(userService.findByIdUser("user")).thenReturn(Optional.of(findResponse));
        Mockito.when(userService.findByIdUser("viewer")).thenReturn(Optional.empty());

        Assertions.assertAll("Soft assertion проверка нескольких вариантов",
                () -> mvc.perform(get("/api/v1/bus_station/user/user"))
                        .andExpect(status().isOk())
                        .andExpect(content().json(findResponseJson)),
                () -> mvc.perform(get("/api/v1/bus_station/user/viewer"))
                        .andExpect(status().isNotFound())
        );
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void findAllUsersControllerTest() throws Exception {
        Mockito.when(userService.findAllUsers()).thenReturn(findAllResponse);

        mvc.perform(get("/api/v1/bus_station/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("user"));
    }

    @WithMockUser(authorities={"USER"})
    @Test
    void findAllUsersReturnEmptyControllerTest() throws Exception {
        Mockito.when(userService.findAllUsers()).thenReturn(findAllResponseEmpty);

        mvc.perform(get("/api/v1/bus_station/user/list"))
                .andExpect(status().isNoContent());
    }
}
