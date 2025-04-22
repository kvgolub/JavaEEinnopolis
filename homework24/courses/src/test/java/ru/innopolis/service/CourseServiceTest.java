package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.repository.CourseRepository;
import ru.innopolis.service.impl.CourseServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {CourseServiceImpl.class})
public class CourseServiceTest {

    @MockitoBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseServiceImpl courseService;

    public CourseServiceTest() throws ParseException {
    }


    @Test
    void createCourseServiceTest() {
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(course);
        var response = courseService.createCourse(courseRequest);

        Assertions.assertEquals(courseResponse.getName(), response.getName());
    }

    @Test
    void findByIdCourseServiceTest() {
        Mockito.when(courseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(course));
        var response = courseService.findByIdCourse(1L);

        Assertions.assertEquals(Optional.of(courseResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllCoursesServiceTest() {
        Mockito.when(courseRepository.findAll()).thenReturn(coursesAll);
        var response = courseService.findAllCourses();

        Assertions.assertEquals(coursesResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateCourseServiceTest() {
        Mockito.when(courseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(course));
        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(courseNew);
        var response = courseService.updateCourse(1L, courseRequestNew);

        Assertions.assertEquals(Optional.of(courseResponseNew).orElseThrow().getName(), response.orElseThrow().getName());
    }

    @Test
    void deleteByIdCourseServiceTest() {
        var response = courseService.deleteByIdCourse(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllCoursesServiceTest() {
        var response = courseService.deleteAllCourses();

        Assertions.assertEquals(1, response);
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final Course course = new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);
    private final CourseRequest courseRequest = new CourseRequest("Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);
    private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);

    // findAll
    private final List<Course> coursesAll = List.of(
            new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new Course(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true),
            new Course(3L, "Программирование на языке C++", sdf.parse("2024-03-15T11:00:00.000+02:00"), true)
    );
    private final List<CourseResponse> coursesResponseAll = List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true),
            new CourseResponse(3L, "Программирование на языке C++", sdf.parse("2024-03-15T11:00:00.000+02:00"), true)
    );

    // update
    private final Course courseNew = new Course(1L, "Программирование на языке GO", sdf.parse("2024-03-20T10:30:00.000+02:00"), true);
    private final CourseRequest courseRequestNew = new CourseRequest("Программирование на языке GO", sdf.parse("2024-03-20T10:30:00.000+02:00"), true);
    private final CourseResponse courseResponseNew = new CourseResponse(1L, "Программирование на языке GO", sdf.parse("2024-03-20T10:30:00.000+02:00"), true);

}
