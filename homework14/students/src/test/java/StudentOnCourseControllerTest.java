import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.StudentOnCourseControllerImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentOnCourse;
import ru.innopolis.service.impl.StudentOnCourseServiceImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {StudentOnCourseControllerImpl.class})
public class StudentOnCourseControllerTest {

    @MockitoBean
    private StudentOnCourseServiceImpl studentsOnCoursesService;

    @Autowired
    private MockMvc mvc;


    @Test
    void createStudentInManyCoursesControllerTest() throws Exception {
        Mockito.when(studentsOnCoursesService.createStudentInManyCourses(Mockito.any(StudentOnCourseRequest.class))).thenReturn(studentOnCourseResponse);

        mvc.perform(post("/api/v1/students_on_courses")
                        .contentType("application/json")
                        .content(requestStudentsOnCourses)
                )
                .andExpect(status().isCreated());
    }


    private final StudentOnCourseResponse studentOnCourseResponse = new StudentOnCourseResponse(
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

    private final String requestStudentsOnCourses = """
                            {
                            	"studentId": 1,
                                "coursesId": [1, 2]
                            }
                        """;

}
