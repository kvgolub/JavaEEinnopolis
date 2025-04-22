package ru.innopolis.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.AccountControllerImpl;
import ru.innopolis.dto.account.AccountResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.service.impl.AccountServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {AccountControllerImpl.class})
public class AccountControllerTest {

    @MockitoBean
    private AccountServiceImpl accountService;

    @Autowired
    private MockMvc mvc;

    public AccountControllerTest() throws ParseException {
    }

    @WithMockUser(authorities="USER")
    @Test
    void getCoursesForAccount() throws Exception {
        Mockito.when(accountService.findOneStudentAllCourses(Mockito.any(String.class))).thenReturn(accountResponse);

        mvc.perform(
                get("/api/v1/account/personal")
                )
                .andExpect(status().isOk());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    private final AccountResponse accountResponse = new AccountResponse(List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    ));

}
