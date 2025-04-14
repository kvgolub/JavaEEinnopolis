package ru.innopolis.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.UserControllerImpl;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {UserControllerImpl.class})
public class UserControllerTest {

    @MockitoBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mvc;


    @WithMockUser(authorities={"USER"})
    @Test
    void findByIdUserControllerTest() throws Exception {
        Mockito.when(userService.findByIdUser(Mockito.any(String.class))).thenReturn(Optional.of(userResponse));

        mvc.perform(get("/api/v1/bus_station/user/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(userResponseJson));
    }

    @WithMockUser(authorities={"ADMIN"})
    @Test
    void findAllUsersControllerTest() throws Exception {
        Mockito.when(userService.findAllUsers()).thenReturn(userResponseAll);

        mvc.perform(get("/api/v1/bus_station/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }


    // find
    private final UserResponse userResponse = new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER"));
    private final String userResponseJson = """
                            {
                            	"username": "user",
                                "password": "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu",
                                "enabled": true,
                                "authorities": [
                                    "USER"
                                ]
                            }
                        """;

    // findAll
    private final List<UserResponse> userResponseAll = List.of(
            new UserResponse("user", "$2a$04$W6JjJyBbrKjMkqEc5oUG8OuR.VdRg6d5VGdjsryLSuWELio.4/Jhu", true, List.of("USER")),
            new UserResponse("admin", "$2a$04$hOkbGUaPwRKAYb7sVqI4zuEYAnwPENWsW0YUmwX9JOye/5uNVFPdS", true, List.of("ADMIN"))
    );

}
