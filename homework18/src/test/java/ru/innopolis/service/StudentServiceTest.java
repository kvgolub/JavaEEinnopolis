package ru.innopolis.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {StudentServiceImpl.class})
public class StudentServiceTest {

    @MockitoBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentService;


    @Test
    void createStudentServiceTest() {
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        var response = studentService.createStudent(studentRequest);

        Assertions.assertEquals(studentResponse.getName(), response.getName());
    }

    @Test
    void findByIdStudentServiceTest() {
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        var response = studentService.findByIdStudent(1L);

        Assertions.assertEquals(Optional.of(studentResponse).orElseThrow().getId(), response.orElseThrow().getId());
    }

    @Test
    void findAllStudentsServiceTest() {
        Mockito.when(studentRepository.findAll()).thenReturn(studentsAll);
        var response = studentService.findAllStudents();

        Assertions.assertEquals(studentResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateStudentServiceTest() {
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(studentNew);
        var response = studentService.updateStudent(1L, studentRequestNew);

        Assertions.assertEquals(Optional.of(studentResponseNew).orElseThrow().getName(), response.orElseThrow().getName());
    }

    @Test
    void deleteByIdStudentServiceTest() {
        var response = studentService.deleteByIdStudent(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllStudentsServiceTest() {
        var response = studentService.deleteAllStudents();

        Assertions.assertEquals(1, response);
    }

    @Test
    void findStudentsCertainAgeServiceTest() {
        Mockito.when(studentRepository.queryStudentsByAgeGreaterThanEqual(Mockito.any(Integer.class))).thenReturn(studentCertainAge);
        var response = studentService.findStudentsCertainAge(30);

        Assertions.assertEquals(Optional.of(studentCertainAgeResponse).orElseThrow().size(), response.size());
    }


    // create
    private final Student student = new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru");
    private final StudentRequest studentRequest = new StudentRequest("Иванов", "Иван", "Иванович", 35, "ivanov@mail.ru");
    private final StudentResponse studentResponse = new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru");


    // findAll
    private final List<Student> studentsAll = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
            new Student(2L, "Петров", "Петр", "Петрович", 25,"petrov@mail.ru"),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );
    private final List<StudentResponse> studentResponseAll = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
            new StudentResponse(2L, "Петров", "Петр", "Петрович", 25, "petrov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );

    // update
    private final Student studentNew = new Student(1L, "Федоров", "Федор", "Федорович", 20,"fedorov@mail.ru");
    private final StudentRequest studentRequestNew = new StudentRequest("Федоров", "Федор", "Федорович", 20, "fedorov@mail.ru");
    private final StudentResponse studentResponseNew = new StudentResponse(1L, "Федоров", "Федор", "Федорович", 20,"fedorov@mail.ru");

    // certainAge
    private final List<Student> studentCertainAge = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );
    private final List<StudentResponse> studentCertainAgeResponse = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", 40,"sidorov@mail.ru")
    );

}
