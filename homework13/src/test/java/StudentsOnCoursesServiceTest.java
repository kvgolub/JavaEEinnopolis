import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.App;
import ru.innopolis.entity.Student;
import ru.innopolis.service.StudentService;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {App.class})
@AutoConfigureJdbc
public class StudentsOnCoursesServiceTest {

    @Autowired
    private StudentsOnCoursesService studentsOnCoursesService;

    @Autowired
    private StudentService studentService;


    @Test
    void createStudentsOnCoursesTest() {
        studentService.createStudent(newStudent);
        Long studentId = studentService.findAllStudents().get(0).getId();

        var response = studentsOnCoursesService.createStudentsOnCourses(studentId, List.of(1L, 2L));

        Assertions.assertEquals(studentId, response.get(0).getStudentId());
        Assertions.assertEquals(2, response.size());
    }

    private final Student newStudent = new Student("Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru");

}
