import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.studentCourse.AllStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.impl.CourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentCourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.impl.StudentCourseServiceImpl;

import java.util.List;


@SpringBootTest(classes = {StudentCourseServiceImpl.class})
public class StudentCourseServiceTest {

    @MockitoBean
    private StudentCourseRepositoryImpl studentCourseRepository;

    @MockitoBean
    private StudentRepositoryImpl studentRepository;

    @MockitoBean
    private CourseRepositoryImpl courseRepository;

    @Autowired
    private StudentCourseServiceImpl studentCourseService;


    @Test
    void createStudentManyCoursesServiceTest() {
        Mockito.when(studentRepository.findByIdStudent(Mockito.any(Long.class))).thenReturn(studentFromDb);
        Mockito.when(studentCourseRepository.findOneStudentAllCourses(Mockito.any(Long.class))).thenReturn(studentCourseList);
        Mockito.when(courseRepository.findByIdCourse(Mockito.any(Long.class))).thenReturn(course);

        Mockito.when(studentCourseRepository.createStudentCourse(Mockito.any(StudentCourse.class))).thenReturn(studentCourse);
        var response = studentCourseService.createOneStudentManyCourses(oneStudentManyCoursesRequest);

        Assertions.assertEquals(oneStudentManyCoursesResponse.getName(), response.getName());
    }

    @Test
    void findAllStudentsOneCourseServiceTest() {
        Mockito.when(studentRepository.findByIdStudent(Mockito.any(Long.class))).thenReturn(studentFromDb);
        Mockito.when(studentCourseRepository.findOneStudentAllCourses(Mockito.any(Long.class))).thenReturn(studentCourseList);
        Mockito.when(courseRepository.findByIdCourse(Mockito.any(Long.class))).thenReturn(course);

        Mockito.when(studentCourseRepository.findAllStudentsOneCourse(Mockito.any(Long.class))).thenReturn(studentCourseList);
        var response = studentCourseService.findAllStudentsOneCourse(1L);

        Assertions.assertEquals(allStudentsOneCourseResponse.getName(), response.getName());
    }


    StudentCourse studentCourse = new StudentCourse( 1L ,1L, 1L);

    private final OneStudentManyCoursesRequest oneStudentManyCoursesRequest = new OneStudentManyCoursesRequest(1L, List.of(1L));
    private final OneStudentManyCoursesResponse oneStudentManyCoursesResponse = new OneStudentManyCoursesResponse(
            1L,
            "Иванов",
            "Иван",
            "Иванович",
            "ivanov@mail.ru",
            List.of(
                    new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true),
                    new CourseResponse(2L, "Программирование на языке PYTHON", "2024-04-01 12:00:00", true)
            )
    );

    private final Student studentFromDb = new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru");
    //private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);
    private final Course course = new Course(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);

    private final List<StudentCourse> studentCourseList = List.of(
            new StudentCourse(1L,1L,1L),
            new StudentCourse(2L,1L,2L)
    );

    private final AllStudentsOneCourseResponse allStudentsOneCourseResponse = new AllStudentsOneCourseResponse(
            1L,
            "Программирование на языке JAVA",
            "2024-03-01 10:00:00",
            true,
            List.of(
                    new StudentResponse(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru"),
                    new StudentResponse(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru")
            )
    );

}
