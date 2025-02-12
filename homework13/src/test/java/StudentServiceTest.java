import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.App;
import ru.innopolis.entity.Student;
import ru.innopolis.service.StudentService;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {App.class})
@AutoConfigureJdbc
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;


    @Order(1)
    @Test
    void createNoteTest() {
        var response = studentService.createStudent(student);

        Assertions.assertTrue(response);
    }

    @Order(2)
    @Test
    void findByIdNoteTest() {
        var response = studentService.findByIdStudent(1L);

        Assertions.assertEquals(1L, response.getId());
    }

    @Order(3)
    @Test
    void findAllNoteTest() {
        var response = (long) studentService.findAllStudents().size();

        Assertions.assertEquals(1, response);
    }

    @Order(4)
    @Test
    void updateNoteTest() {
        var response = studentService.updateStudent(1L, student);

        Assertions.assertTrue(response);
    }

    @Order(5)
    @Test
    void deleteByIdNoteTest() {
        var response = studentService.deleteByIdStudent(1L);

        Assertions.assertTrue(response);
    }

    @Order(6)
    @Test
    void deleteAllNoteTest() {
        studentService.createStudent(student);
        var response = studentService.deleteAllStudents();

        Assertions.assertEquals(1, response);
    }

    private final Student student = new Student(1L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru");

}
