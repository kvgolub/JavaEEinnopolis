package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.studentCourse.ManyStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.CourseRepository;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.impl.StudentCourseServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest(classes = {StudentCourseServiceImpl.class})
public class StudentCourseServiceTest {

    @MockitoBean
    private StudentCourseRepository studentCourseRepository;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseServiceImpl studentCourseService;

    public StudentCourseServiceTest() throws ParseException {
    }


    @Test
    void createOneStudentManyCoursesServiceTest() {
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(courseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(course));
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        Mockito.when(studentCourseRepository.save(Mockito.any(StudentCourse.class))).thenReturn(studentCourse);
        var response = studentCourseService.createOneStudentManyCourses(oneStudentManyCoursesRequest);

        Assertions.assertEquals(oneStudentManyCoursesResponse.getName(), response.getName());
    }

    @Test
    void findAllStudentsOneCourseServiceTest() {
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(courseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(course));
        //Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        Mockito.when(studentCourseRepository.findStudentCourseByCourseId(Mockito.any(Long.class))).thenReturn(studentCourseList);
        var response = studentCourseService.findAllStudentsOneCourse(1L);

        Assertions.assertEquals(manyStudentsOneCourseResponse.getName(), response.getName());
        Assertions.assertEquals(manyStudentsOneCourseResponse.getStudents().size(), response.getStudents().size());
    }

    @Test
    void findAllStudentsCertainAgeOneCourseServiceTest() {
        Mockito.when(studentRepository.queryStudentsByAgeGreaterThanEqual(Mockito.any(Integer.class))).thenReturn(studentCertainAgeList);
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(courseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(course));
        Mockito.when(studentCourseRepository.findStudentCourseByCourseId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        var response = studentCourseService.findAllStudentsCertainAgeOneCourse(30, 1L);

        Assertions.assertEquals(manyStudentsCertainAgeOneCourseResponse.getName(), response.getName());
        Assertions.assertEquals(manyStudentsCertainAgeOneCourseResponse.getStudents().size(), response.getStudents().size());
    }

    @Test
    void findAllStudentsTwoOrMoreCoursesServiceTest() {
        Mockito.when(studentRepository.findStudentsByIdIn(Mockito.any(Set.class))).thenReturn(List.of(student));
        Mockito.when(courseRepository.findAllById(Mockito.any(List.class))).thenReturn(List.of(course));
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        var response = studentCourseService.findAllStudentsTwoOrMoreCoursesResponse(2L);

        Assertions.assertEquals(courseCountResponseList.get(0).getName(), response.get(0).getName());
        Assertions.assertEquals(courseCountResponseList.size(), response.size());
    }

    @Test
    void findStudentByNameAndCourseByNameServiceTest() {
        Mockito.when(studentRepository.findStudentsByIdIn(Mockito.any(Set.class))).thenReturn(List.of(student));
        Mockito.when(courseRepository.findAllById(Mockito.any(List.class))).thenReturn(List.of(course));
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        var response = studentCourseService.findStudentsByNameAndCoursesByName("Иван", "JAVA");

        Assertions.assertEquals(studentNameCourseNameResponseList.get(0).getName(), response.get(0).getName());
        Assertions.assertEquals(studentNameCourseNameResponseList.size(), response.size());
    }

    @Test
    void findAllStudentsCertainAgeTwoOrMoreCoursesServiceTest() {
        Mockito.when(studentRepository.findStudentsByIdIn(Mockito.any(Set.class))).thenReturn(List.of(student));
        Mockito.when(courseRepository.findAllById(Mockito.any(List.class))).thenReturn(List.of(course));
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        var response = studentCourseService.findAllStudentsCertainAgeTwoOrMoreCourses(30, 1L);

        Assertions.assertEquals(ageCourseCountResponseList.get(0).getName(), response.get(0).getName());
        Assertions.assertEquals(ageCourseCountResponseList.size(), response.size());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final Student student = new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru");
    private final Course course = new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);
    private final List<StudentCourse> studentCourseList = List.of(
            new StudentCourse(1L,1L,1L),
            new StudentCourse(2L,1L,2L)
    );

    private final StudentCourse studentCourse = new StudentCourse( 1L ,1L, 1L);
    private final OneStudentManyCoursesRequest oneStudentManyCoursesRequest = new OneStudentManyCoursesRequest(1L, List.of(1L, 2L));
    private final OneStudentManyCoursesResponse oneStudentManyCoursesResponse = new OneStudentManyCoursesResponse(
            1L,
            "Иванов",
            "Иван",
            "Иванович",
            35,
            "ivanov@mail.ru",
            List.of(
                    new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                    new Course(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
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
    private final List<Student> studentCertainAgeList = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );
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
                            new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                            new Course(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
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
                            new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true)
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
                            new Course(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
                            new Course(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
                    )
            )
    );

}
