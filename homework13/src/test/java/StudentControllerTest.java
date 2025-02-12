import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.StudentController;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.StudentService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = {StudentController.class, StudentService.class, StudentRepository.class})
@AutoConfigureMockMvc
public class StudentControllerTest {

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private MockMvc mvc;


    @Test
    void createStudentTest() throws Exception {
        Mockito.when(studentService.createStudent(student)).thenReturn(true);

        mvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(requestStudent)
                )
                .andExpect(result -> result.getResponse().getStatus());
    }

    @Test
    void findByIdStudentTest() throws Exception {
        Mockito.when(studentService.findByIdStudent(1L)).thenReturn(student);

        mvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestStudent));
    }

    @Test
    void findAllStudentsTest() throws Exception {
        Mockito.when(studentService.findAllStudents()).thenReturn(students);

        mvc.perform(get("/api/v1/student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateStudentTest() throws Exception {
        Mockito.when(studentService.updateStudent(1L, studentNew)).thenReturn(true);

        mvc.perform(put("/api/v1/student/1")
                        .contentType("application/json")
                        .content(requestStudentUpdate)
                )
                .andExpect(result -> result.getResponse().getStatus());
    }

    @Test
    void deleteByIdStudentTest() throws Exception {
        Mockito.when(studentService.deleteByIdStudent(1L)).thenReturn(true);

        mvc.perform(delete("/api/v1/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllStudentsTest() throws Exception {
        Mockito.when(studentService.deleteAllStudents()).thenReturn(1);

        mvc.perform(delete("/api/v1/student/all"))
                .andExpect(status().isOk());
    }

    private final Student student = new Student(1L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru");

    private final List<Student> students = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru"),
            new Student(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru"),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru")
    );

    private final String requestStudent = """
                            {
                            	"id": 1,
                            	"surname": "Сидоров",
                            	"name": "Сидр",
                            	"patronymic": "Сидорович",
                            	"email": "sidorov@mail.ru"
                            }
                        """;

    private final String requestStudentUpdate = """
                            {
                            	"surname": "Федоров",
                            	"name": "Федор",
                            	"patronymic": "Федорович",
                            	"email": "fedorov@mail.ru"
                            }
                        """;

    private final Student studentNew = new Student(1L, "Федоров", "Федор", "Федорович", "fedorov@mail.ru");

}
