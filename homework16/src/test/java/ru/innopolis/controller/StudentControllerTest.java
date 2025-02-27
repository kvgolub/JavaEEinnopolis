package ru.innopolis.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.StudentControllerImpl;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {StudentControllerImpl.class})
public class StudentControllerTest {

    @MockitoBean
    private StudentServiceImpl studentService;

    @Autowired
    private MockMvc mvc;


    @Test
    void createStudentControllerTest() throws Exception {
        Mockito.when(studentService.createStudent(Mockito.any(StudentRequest.class))).thenReturn(studentResponse);

        mvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(studentRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findByIdStudentControllerTest() throws Exception {
        Mockito.when(studentService.findByIdStudent(Mockito.any(Long.class))).thenReturn(Optional.of(studentResponse));

        mvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(studentResponseJson));
    }

    @Test
    void findAllStudentsControllerTest() throws Exception {
        Mockito.when(studentService.findAllStudents()).thenReturn(studentsResponseAll);

        mvc.perform(get("/api/v1/student/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateStudentControllerTest() throws Exception {
        Mockito.when(studentService.updateStudent(Mockito.any(Long.class), Mockito.any(StudentRequest.class))).thenReturn(Optional.of(studentResponseNew));

        mvc.perform(put("/api/v1/student/1")
                        .contentType("application/json")
                        .content(studentRequestNewBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(studentResponseNewJson));
    }

    @Test
    void deleteByIdStudentControllerTest() throws Exception {
        Mockito.when(studentService.deleteByIdStudent(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllStudentsControllerTest() throws Exception {
        Mockito.when(studentService.deleteAllStudents()).thenReturn(1);

        mvc.perform(delete("/api/v1/student/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentOver30yearsOldControllerTest() throws Exception {
        Mockito.when(studentService.getStudentOver30yearsOld(Mockito.any(Integer.class))).thenReturn(studentsOver30yearsOld);

        mvc.perform(get("/api/v1/student/over30yo/30"))
                .andExpect(status().isOk());
    }


    // create
    private final StudentResponse studentResponse = new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35, "ivanov@mail.ru");
    private final String studentRequestBody = """
                            {
                            	"surname": "Иванов",
                            	"name": "Иван",
                                "patronymic": "Иванович",
                                "age": 35,
                                "email": "ivanov@mail.ru"
                            }
                        """;

    // find
    private final String studentResponseJson = """
                            {
                            	"id": 1,
                            	"surname": "Иванов",
                            	"name": "Иван",
                                "patronymic": "Иванович",
                                "age": 35,
                                "email": "ivanov@mail.ru"
                            }
                        """;

    // findAll
    private final List<StudentResponse> studentsResponseAll = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35, "ivanov@mail.ru"),
            new StudentResponse(2L, "Петров", "Петр", "Петрович", 25,"petrov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );

    // update
    private final StudentResponse studentResponseNew = new StudentResponse(1L, "Федоров", "Федор", "Федорович", 20,"fedorov@mail.ru");
    private final String studentRequestNewBody = """
                            {
                                "surname": "Федоров",
                            	"name": "Федор",
                                "patronymic": "Федорович",
                                "age": 20,
                                "email": "fedorov@mail.ru"
                            }
                        """;
    private final String studentResponseNewJson = """
                            {
                                "id": 1,
                                "surname": "Федоров",
                            	"name": "Федор",
                                "patronymic": "Федорович",
                                "age": 20,
                                "email": "fedorov@mail.ru"
                            }
                        """;

    // over30yo
    private final List<StudentResponse> studentsOver30yearsOld = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35, "ivanov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );

}
