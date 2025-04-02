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
import ru.innopolis.dto.user.UserRequest;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {UserControllerImpl.class})
public class UserControllerTest {

    @MockitoBean
    private UserServiceImpl accountService;

    @Autowired
    private MockMvc mvc;

    public UserControllerTest() throws ParseException {
    }

    @WithMockUser(authorities="USER")
    @Test
    void getCoursesForAccount() throws Exception {
        Mockito.when(accountService.findOneStudentAllCourses(Mockito.any(String.class))).thenReturn(userResponse);

        mvc.perform(
                get("/api/v1/user/account")
                )
                .andExpect(status().isOk());
    }

    @Test
    void createAccount() throws Exception {
        Mockito.when(accountService.createAccount(Mockito.any(UserRequest.class))).thenReturn(true);

        mvc.perform(
                post("/api/v1/user/registration")
                        .contentType("application/json")
                        .content(userRequestBody)
                )
                .andExpect(result -> result.getResponse().getContentAsString());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    private final UserResponse userResponse = new UserResponse(List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    ));

    private final String userRequestBody = """
                            {
                                "username": "ivanov",
                                "password": "12345",
                                "authority": "USER",
                                "studentRequest": {
                                        "surname": "Иванов",
                                        "name": "Иван",
                                        "patronymic": "Иванович",
                                        "age": 35,
                                        "email": "ivanov@mail.ru"
                                    }
                             }
                        """;

}
