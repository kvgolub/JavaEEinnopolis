import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.repository.impl.CourseRepositoryImpl;
import ru.innopolis.service.impl.CourseServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {CourseServiceImpl.class})
public class CourseServiceTest {

    @MockitoBean
    private CourseRepositoryImpl courseRepository;

    @Autowired
    private CourseServiceImpl courseService;


    @Test
    void createCourseServiceTest() {
        Mockito.when(courseRepository.createCourse(Mockito.any(Course.class))).thenReturn(courseFromDb);
        var response = courseService.createCourse(courseRequest);

        Assertions.assertEquals(courseResponse.getName(), response.getName());
    }

    @Test
    void findByIdCourseServiceTest() {
        Mockito.when(courseRepository.findByIdCourse(1L)).thenReturn(courseFromDb);
        var response = courseService.findByIdCourse(1L);

        Assertions.assertEquals(Optional.of(courseFromDb).get().getId(), response.get().getId());
    }

    @Test
    void findAllCoursesServiceTest() {
        Mockito.when(courseRepository.findAllCourses()).thenReturn(coursesAll);
        var response = courseService.findAllCourses();

        Assertions.assertEquals(coursesResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateCourseServiceTest() {
        Mockito.when(courseRepository.findByIdCourse(1L)).thenReturn(courseFromDb);
        Mockito.when(courseRepository.updateCourse(Mockito.any(Course.class))).thenReturn(courseFromDbNew);
        var response = courseService.updateCourse(1L, courseRequestNew);

        Assertions.assertEquals(Optional.of(courseResponseNew).get().getName(), response.get().getName());
    }

    @Test
    void deleteByIdCourseServiceTest() {
        Mockito.when(courseRepository.deleteByIdCourse(1L)).thenReturn(1);
        var response = courseService.deleteByIdCourse(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllCoursesServiceTest() {
        Mockito.when(courseRepository.deleteAllCourses()).thenReturn(1);
        var response = courseService.deleteAllCourses();

        Assertions.assertEquals(1, response);
    }


    private final CourseRequest courseRequest = new CourseRequest("Программирование на языке JAVA", "2024-03-01 10:00:00", true);
    private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);
    private final Course courseFromDb = new Course(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);

    private final List<CourseResponse> coursesResponseAll = List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true),
            new CourseResponse(2L, "Программирование на языке PYTHON", "2024-04-01 12:00:00", true),
            new CourseResponse(3L, "Программирование на языке C++", "2024-03-15 11:00:00", true)
    );
    private final List<Course> coursesAll = List.of(
            new Course(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true),
            new Course(2L, "Программирование на языке PYTHON", "2024-04-01 12:00:00", true),
            new Course(3L, "Программирование на языке C++", "2024-03-15 11:00:00", true)
    );

    private final CourseRequest courseRequestNew = new CourseRequest("Программирование на языке GO", "2024-03-20 10:30:00", true);
    private final CourseResponse courseResponseNew = new CourseResponse(1L, "Программирование на языке GO", "2024-03-20 10:30:00", true);
    private final Course courseFromDbNew = new Course(1L, "Программирование на языке GO", "2024-03-20 10:30:00", true);

}
