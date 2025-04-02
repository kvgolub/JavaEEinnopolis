package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.user.UserRequest;
import ru.innopolis.dto.user.UserResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest(classes = {UserServiceImpl.class})
public class UserServiceTest {

    @MockitoBean
    private StudentCourseRepository studentCourseRepository;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private CoursesClientImpl coursesClient;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private JdbcUserDetailsManager jdbcUserDetailsManager;


    @Autowired
    private UserServiceImpl userService;

    public UserServiceTest() throws ParseException {
    }


    @Test
    void getCoursesForAccountServiceTest() {
        Mockito.when(studentRepository.findStudentByEmail(Mockito.any(String.class))).thenReturn(student);
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(coursesClient.getListCoursesByIds(Mockito.any(Set.class))).thenReturn(coursesResponse);

        var response = userService.findOneStudentAllCourses("ivanov");

        Assertions.assertEquals(userResponse.getCourseResponseList().get(0).getName(), response.getCourseResponseList().get(0).getName());
    }

    @Test
    void createAccountServiceTest() {
        Mockito.when(studentRepository.findStudentByEmail(Mockito.any(String.class))).thenReturn(student);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        var response = userService.createAccount(userRequest);

        Assertions.assertFalse(response);
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    private final Student student = new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru");
    private final List<StudentCourse> studentCourseList = List.of(
            new StudentCourse(1L,1L,1L),
            new StudentCourse(2L,1L,2L)
    );
    private final List<CourseResponse> coursesResponse = List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    );

    private final UserResponse userResponse = new UserResponse(List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    ));

    private final UserRequest userRequest = new UserRequest(
            "ivanov",
            "12345",
            "USER",
            new StudentRequest("Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru")
    );

}
