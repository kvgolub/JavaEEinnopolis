package ru.innopolis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.account.AccountResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.impl.AccountServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest(classes = {AccountServiceImpl.class})
public class AccountServiceTest {

    @MockitoBean
    private StudentCourseRepository studentCourseRepository;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private CoursesClientImpl coursesClient;

    @Autowired
    private AccountServiceImpl accountService;

    public AccountServiceTest() throws ParseException {
    }


    @Test
    void createOneStudentManyCoursesServiceTest() {
        Mockito.when(studentRepository.findStudentByEmail(Mockito.any(String.class))).thenReturn(student);
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(coursesClient.getListCoursesByIds(Mockito.any(Set.class))).thenReturn(coursesResponse);
        Mockito.when(studentCourseRepository.findStudentCourseByStudentId(Mockito.any(Long.class))).thenReturn(studentCourseList);

        var response = accountService.findOneStudentAllCourses("ivanov@mail.ru");

        Assertions.assertEquals(accountResponse.getCourseResponseList().get(0).getName(), response.getCourseResponseList().get(0).getName());
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

    private final AccountResponse accountResponse = new AccountResponse(List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true)
    ));

}
