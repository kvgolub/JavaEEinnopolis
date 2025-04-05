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
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.user.UserCreateRequest;
import ru.innopolis.dto.user.UserAllCoursesResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.user.UserStudentResponse;
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


    @Test
    void createAccountUserControllerTest() throws Exception {
        Mockito.when(accountService.createAccount(Mockito.any(UserCreateRequest.class))).thenReturn(true);

        mvc.perform(
                        post("/api/v1/user/registration")
                                .contentType("application/json")
                                .content(userRequestBody)
                )
                .andExpect(result -> result.getResponse().getContentAsString());
    }

    @WithMockUser(authorities="USER")
    @Test
    void findAllCoursesForUserControllerTest() throws Exception {
        Mockito.when(accountService.findAllCoursesForUser(Mockito.any(String.class))).thenReturn(userAllCoursesResponse);

        mvc.perform(
                get("/api/v1/user/courses")
                )
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities="USER")
    @Test
    void findStudentForUserControllerTest() throws Exception {
        Mockito.when(accountService.findStudentForUser(Mockito.any(String.class))).thenReturn(userStudentResponse);

        mvc.perform(
                        get("/api/v1/user/student")
                )
                .andExpect(status().isOk());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // for createAccountUserControllerTest:
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

    //for findAllCoursesForUserControllerTest:
    private final UserAllCoursesResponse userAllCoursesResponse = new UserAllCoursesResponse(List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    ));

    //for findStudentForUserControllerTest:
    private final UserStudentResponse userStudentResponse = new UserStudentResponse(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35, "ivanov@mail.ru")
    );

}
