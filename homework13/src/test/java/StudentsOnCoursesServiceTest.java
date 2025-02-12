import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.innopolis.App;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.service.StudentService;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(classes = {StudentService.class, StudentRepository.class})
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
        var response = studentsOnCoursesService.createStudentsOnCourses(1L, List.of(1L, 2L));

        Assertions.assertEquals(studentsOnCourses1.getId(), response.get(0).getId());
        Assertions.assertEquals(studentsOnCourses2.getId(), response.get(1).getId());
    }

    private final Student newStudent = new Student(1L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru");

    private final StudentsOnCourses studentsOnCourses1 = new StudentsOnCourses(2L, 1L, 1L);
    private final StudentsOnCourses studentsOnCourses2 = new StudentsOnCourses(3L, 1L, 2L);

}
