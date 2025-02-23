import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.App;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
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

        Assertions.assertEquals(student.getName(), response.getName());
    }

    @Order(2)
    @Test
    void findByIdNoteTest() {
        studentId = studentService.findAllStudents().get(0).getId();
        var response = studentService.findByIdStudent(studentId);

        Assertions.assertEquals(studentId, response.getId());
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
        studentId = studentService.findAllStudents().get(0).getId();
        var response = studentService.updateStudent(studentId, studentNew);

        Assertions.assertEquals(studentNew.getName(), response.getName());
    }

    @Order(5)
    @Test
    void deleteByIdNoteTest() {
        studentId = studentService.findAllStudents().get(0).getId();
        var response = studentService.deleteByIdStudent(studentId);

        Assertions.assertTrue(response);
    }

    @Order(6)
    @Test
    void deleteAllNoteTest() {
        studentService.createStudent(student);
        var response = studentService.deleteAllStudents();

        Assertions.assertEquals(1, response);
    }

    private final Student student = new Student( "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru");
    private final Student studentNew = new Student( "Федоров", "Федор", "Федорович", "fedorov@mail.ru");

    private Long studentId = null;

}
