import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.StudentCourseControllerImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.studentCourse.AllStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.service.impl.StudentCourseServiceImpl;

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


    @Test
    void createStudentManyCoursesControllerTest() throws Exception {
        Mockito.when(studentCourseService.createOneStudentManyCourses(Mockito.any(OneStudentManyCoursesRequest.class))).thenReturn(oneStudentManyCoursesResponse);

        mvc.perform(post("/api/v1/students_on_courses")
                        .contentType("application/json")
                        .content(requestStudentCourse)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findAllStudentsOneCourseControllerTest() throws Exception {
        Mockito.when(studentCourseService.findAllStudentsOneCourse(Mockito.any(Long.class))).thenReturn(allStudentsOneCourseResponse);

        mvc.perform(get("/api/v1/students_on_courses/1"))
                .andExpect(status().isOk());
    }


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

    private final String requestStudentCourse = """
                            {
                            	"studentId": 1,
                                "coursesId": [1, 2]
                            }
                        """;

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
