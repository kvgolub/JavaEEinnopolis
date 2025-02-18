import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {StudentServiceImpl.class})
public class StudentServiceTest {

    @MockitoBean
    private StudentRepositoryImpl studentRepository;

    @Autowired
    private StudentServiceImpl studentService;


    @Test
    void createStudentServiceTest() {
        Mockito.when(studentRepository.createStudent(Mockito.any(Student.class))).thenReturn(studentFromDb);
        var response = studentService.createStudent(studentRequest);

        Assertions.assertEquals(studentResponse.getName(), response.getName());
    }

    @Test
    void findByIdStudentServiceTest() {
        Mockito.when(studentRepository.findByIdStudent(1L)).thenReturn(studentFromDb);
        var response = studentService.findByIdStudent(1L);

        Assertions.assertEquals(Optional.of(studentResponse).get().getId(), response.get().getId());
    }

    @Test
    void findAllStudentsServiceTest() {
        Mockito.when(studentRepository.findAllStudents()).thenReturn(studentsAll);
        var response = studentService.findAllStudents();

        Assertions.assertEquals(studentResponseAll.get(0).getId(), response.get(0).getId());
    }

    @Test
    void updateStudentServiceTest() {
        Mockito.when(studentRepository.findByIdStudent(1L)).thenReturn(studentFromDb);
        Mockito.when(studentRepository.updateStudent(Mockito.any(Student.class))).thenReturn(studentFromDbNew);
        var response = studentService.updateStudent(1L, studentRequestNew);

        Assertions.assertEquals(Optional.of(studentResponseNew).get().getName(), response.get().getName());
    }

    @Test
    void deleteByIdStudentServiceTest() {
        Mockito.when(studentRepository.deleteByIdStudent(1L)).thenReturn(1);
        var response = studentService.deleteByIdStudent(1L);

        Assertions.assertTrue(response);
    }

    @Test
    void deleteAllStudentsServiceTest() {
        Mockito.when(studentRepository.deleteAllStudents()).thenReturn(1);
        var response = studentService.deleteAllStudents();

        Assertions.assertEquals(1, response);
    }

    private final StudentRequest studentRequest = new StudentRequest("Иванов", "Иван", "Иванович", "ivanov@mail.ru");
    private final StudentResponse studentResponse = new StudentResponse(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru");
    private final Student studentFromDb = new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru");

    private final List<StudentResponse> studentResponseAll = List.of(
            new StudentResponse(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru"),
            new StudentResponse(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru"),
            new StudentResponse(3L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru")
    );
    private final List<Student> studentsAll = List.of(
            new Student(1L, "Иванов", "Иван", "Иванович", "ivanov@mail.ru"),
            new Student(2L, "Петров", "Петр", "Петрович", "petrov@mail.ru"),
            new Student(3L, "Сидоров", "Сидр", "Сидорович", "sidorov@mail.ru")
    );

    private final StudentRequest studentRequestNew = new StudentRequest("Федоров", "Федор", "Федорович", "fedorov@mail.ru");
    private final StudentResponse studentResponseNew = new StudentResponse(1L, "Федоров", "Федор", "Федорович", "fedorov@mail.ru");
    private final Student studentFromDbNew = new Student(1L, "Федоров", "Федор", "Федорович", "fedorov@mail.ru");

}
