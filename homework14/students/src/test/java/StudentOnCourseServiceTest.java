import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.AppStudent;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentOnCourse;
import ru.innopolis.repository.impl.StudentOnCourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.impl.StudentOnCourseServiceImpl;

import java.util.List;


@SpringBootTest(classes = {StudentOnCourseServiceImpl.class})
public class StudentOnCourseServiceTest {

    @MockitoBean
    private StudentOnCourseRepositoryImpl studentOnCourseRepository;

    @MockitoBean
    private StudentRepositoryImpl studentRepository;

    @MockitoBean
    private CoursesClientImpl coursesClient;

    @Autowired
    private StudentOnCourseServiceImpl studentOnCourseService;


    @Test
    void createStudentInManyCoursesServiceTest() {
        Mockito.when(studentRepository.findByIdStudent(Mockito.any(Long.class))).thenReturn(studentFromDb);
        Mockito.when(studentOnCourseRepository.findAllCoursesForStudent(Mockito.any(Long.class))).thenReturn(studentOnCourseList);
        Mockito.when(coursesClient.getCourse(Mockito.any(Long.class))).thenReturn(courseResponse);

        Mockito.when(studentOnCourseRepository.createStudentOnCourse(Mockito.any(StudentOnCourse.class))).thenReturn(studentOnCourse);
        var response = studentOnCourseService.createStudentInManyCourses(studentOnCourseRequest);

        Assertions.assertEquals(studentOnCourseResponse.getName(), response.getName());
    }


    StudentOnCourse studentOnCourse = new StudentOnCourse( 1L ,1L, 1L);

    private final StudentOnCourseRequest studentOnCourseRequest = new StudentOnCourseRequest(1L, List.of(1L));
    private final StudentOnCourseResponse studentOnCourseResponse = new StudentOnCourseResponse(
            1L,
            "Иванов",
            "Иван",
            "Иванович",
            "ivanov@mail.ru",
            List.of(
                    new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true)
                    //new CourseResponse(2L, "Программирование на языке PYTHON", "2024-04-01 12:00:00", true)
            )
    );

    private final Student studentFromDb = new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru");
    private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);

    private final List<StudentOnCourse> studentOnCourseList = List.of(
            new StudentOnCourse(1L,1L,1L)
            //new StudentOnCourse(2L,1L,2L)
    );

}
