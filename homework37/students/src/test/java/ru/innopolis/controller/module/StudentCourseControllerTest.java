package ru.innopolis.controller.module;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.StudentCourseControllerImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.studentCourse.ManyStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.service.impl.StudentCourseServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {StudentCourseControllerImpl.class})
public class StudentCourseControllerTest {

    @MockitoBean
    private StudentCourseServiceImpl studentCourseService;

    @Autowired
    private MockMvc mvc;

    public StudentCourseControllerTest() throws ParseException {
    }


    @Test
    void createOneStudentManyCoursesControllerTest() throws Exception {
        Mockito.when(studentCourseService.createOneStudentManyCourses(Mockito.any(OneStudentManyCoursesRequest.class))).thenReturn(oneStudentManyCoursesResponse);

        mvc.perform(post("/api/v1/students_on_courses")
                        .contentType("application/json")
                        .content(requestStudentCourse)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findAllStudentsOneCourseControllerTest() throws Exception {
        Mockito.when(studentCourseService.findAllStudentsOneCourse(Mockito.any(Long.class))).thenReturn(manyStudentsOneCourseResponse);

        mvc.perform(get("/api/v1/students_on_courses/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllStudentsCertainAgeOneCourseControllerTest() throws Exception {
        Mockito.when(studentCourseService.findAllStudentsCertainAgeOneCourse(Mockito.any(Integer.class), Mockito.any(Long.class))).thenReturn(manyStudentsCertainAgeOneCourseResponse);

        mvc.perform(get("/api/v1/students_on_courses/age_more_than")
                        .param("id", "1")
                        .param("age", "30")
                )
                .andExpect(status().isOk());
    }

    @Test
    void findAllStudentsTwoOrMoreCoursesControllerTest() throws Exception {
        Mockito.when(studentCourseService.findAllStudentsTwoOrMoreCoursesResponse(Mockito.any(Long.class))).thenReturn(courseCountResponseList);

        mvc.perform(get("/api/v1/students_on_courses/count/2"))
                .andExpect(status().isOk());
    }

    @Test
    void findStudentByNameAndCourseByNameControllerTest() throws Exception {
        Mockito.when(studentCourseService.findStudentsByNameAndCoursesByName(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(studentNameCourseNameResponseList);

        mvc.perform(get("/api/v1/students_on_courses/search_by_name")
                        .param("studentName", "Иван")
                        .param("courseName", "JAVA")
                )
                .andExpect(status().isOk());
    }

    @Test
    void findAllStudentsCertainAgeTwoOrMoreCoursesControllerTest() throws Exception {
        Mockito.when(studentCourseService.findAllStudentsCertainAgeTwoOrMoreCourses(Mockito.any(Integer.class), Mockito.any(Long.class))).thenReturn(ageCourseCountResponseList);

        mvc.perform(get("/api/v1/students_on_courses/age_more_than_and_count")
                        .param("age", "30")
                        .param("courseCount", "2")
                )
                .andExpect(status().isOk());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");


    // create
    private final String requestStudentCourse = """
                            {
                            	"studentId": 1,
                                "coursesId": [1, 2]
                            }
                        """;
    private final OneStudentManyCoursesResponse oneStudentManyCoursesResponse = new OneStudentManyCoursesResponse(
            1L,
            "Иванов",
            "Иван",
            "Иванович",
            35,
            "ivanov@mail.ru",
            List.of(
                    new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                    new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
            )
    );


    // findAll
    private final ManyStudentsOneCourseResponse manyStudentsOneCourseResponse = new ManyStudentsOneCourseResponse(
            1L,
            "Программирование на языке JAVA",
            sdf.parse("2024-03-01T10:00:00.000+02:00"),
            true,
            List.of(
                    new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
                    new Student(2L, "Петров", "Петр", "Петрович", 25,"petrov@mail.ru")
            )
    );

    // certainAge
    private final ManyStudentsOneCourseResponse manyStudentsCertainAgeOneCourseResponse = new ManyStudentsOneCourseResponse(
            1L,
            "Программирование на языке JAVA",
            sdf.parse("2024-03-01T10:00:00.000+02:00"),
            true,
            List.of(
                    new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
                    new Student(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
            )
    );

    // courseCount
    private final List<OneStudentManyCoursesResponse> courseCountResponseList = List.of(
            new OneStudentManyCoursesResponse(
                    1L,
                    "Иванов",
                    "Иван",
                    "Иванович",
                    35,
                    "ivanov@mail.ru",
                    List.of(
                            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
                    )
            ),
            new OneStudentManyCoursesResponse(
                    2L,
                    "Петров",
                    "Петр",
                    "Петрович",
                    45,
                    "petrov@mail.ru",
                    List.of(
                            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
                    )
            )
    );

    // studentNameCourseName
    private final List<OneStudentManyCoursesResponse> studentNameCourseNameResponseList = List.of(
            new OneStudentManyCoursesResponse(
                    1L,
                    "Иванов",
                    "Иван",
                    "Иванович",
                    35,
                    "ivanov@mail.ru",
                    List.of(
                            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true)
                    )
            )
    );

    // ageCourseCount
    private final List<OneStudentManyCoursesResponse> ageCourseCountResponseList = List.of(
            new OneStudentManyCoursesResponse(
                    1L,
                    "Иванов",
                    "Иван",
                    "Иванович",
                    35,
                    "ivanov@mail.ru",
                    List.of(
                            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
                    )
            )
    );
}
