import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.StudentController;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = StudentController.class)
@ContextConfiguration(classes = StudentController.class)
public class StudentControllerTest {

    @MockitoBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    void createStudentTest() throws Exception {
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        mvc.perform(post("/api/v1/student")
                        .contentType("application/json")
                        .content(requestStudent)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findByIdStudentTest() throws Exception {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestStudent));
    }

    @Test
    void findAllStudentsTest() throws Exception {
        Mockito.when(studentRepository.findAll()).thenReturn(students);

        mvc.perform(get("/api/v1/student/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateStudentTest() throws Exception {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mvc.perform(put("/api/v1/student/1")
                        .contentType("application/json")
                        .content(requestStudent)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteByIdStudentTest() throws Exception {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mvc.perform(delete("/api/v1/student/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllStudentsTest() throws Exception {
        Mockito.when(studentRepository.findAll()).thenReturn(students);

        mvc.perform(delete("/api/v1/student/all"))
                .andExpect(status().isOk());
    }

    private Student student = new Student(1L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru", Set.of("Курс 1", "Курс 2"));

    private List<Student> students = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru", Set.of("Курс 1", "Курс 2")),
            new Student(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru", Set.of("Курс 3", "Курс 4")),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru", Set.of("Курс 5", "Курс 6"))
    );

    private String requestStudent = """
                            {
                            	"id": 1,
                            	"surname": "Сидоров",
                            	"name": "Сидр",
                            	"patronymic": "Сидорович",
                            	"email": "sidorov@mail.ru",
                            	"course": [
                            		"Курс 1",
                            		"Курс 2"
                            	]
                            }
                        """;

}
