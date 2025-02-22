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
        Mockito.when(studentService.createStudent(studentRequest)).thenReturn(studentResponse);

        mvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(studentRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findByIdStudentControllerTest() throws Exception {
        Mockito.when(studentService.findByIdStudent(1L)).thenReturn(Optional.of(studentResponse));

        mvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(studentRequestBody));
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
                .andExpect(content().json(studentRequestNewBody));
    }

    @Test
    void deleteByIdStudentControllerTest() throws Exception {
        Mockito.when(studentService.deleteByIdStudent(1L)).thenReturn(true);

        mvc.perform(delete("/api/v1/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllStudentsControllerTest() throws Exception {
        Mockito.when(studentService.deleteAllStudents()).thenReturn(1);

        mvc.perform(delete("/api/v1/student/all"))
                .andExpect(status().isOk());
    }

    private final StudentRequest studentRequest = new StudentRequest("Иванов", "Иван", "Иванович", "ivanov@mail.ru");
    private final StudentResponse studentResponse = new StudentResponse(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru");

    private final String studentRequestBody = """
                            {
                            	"surname": "Иванов",
                            	"name": "Иван",
                                "patronymic": "Иванович",
                                "email": "ivanov@mail.ru"
                            }
                        """;

    private final List<StudentResponse> studentsResponseAll = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru"),
            new StudentResponse(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru")
    );

    private final StudentResponse studentResponseNew = new StudentResponse(1L, "Федоров", "Федор", "Федорович", "fedorov@mail.ru");

    private final String studentRequestNewBody = """
                            {
                                "surname": "Федоров",
                            	"name": "Федор",
                                "patronymic": "Федорович",
                                "email": "fedorov@mail.ru"
                            }
                        """;

}
