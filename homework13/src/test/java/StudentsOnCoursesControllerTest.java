import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.StudentsOnCoursesController;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.repository.StudentsOnCoursesRepository;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentsOnCoursesController.class)
@ContextConfiguration(classes = {StudentsOnCoursesController.class})
@AutoConfigureMockMvc
public class StudentsOnCoursesControllerTest {

    @MockitoBean
    private StudentsOnCoursesService studentsOnCoursesService;

    @Autowired
    private MockMvc mvc;


    @Test
    void createStudentsOnCoursesTest() throws Exception {
        Mockito.when(studentsOnCoursesService.createStudentsOnCourses(1L, List.of(1L, 2L))).thenReturn(List.of(studentsOnCourses1, studentsOnCourses2));

        mvc.perform(post("/api/v1/students_on_courses")
                        .contentType("application/json")
                        .content(requestStudentsOnCourses)
                )
                .andExpect(status().isCreated());
    }

    private final StudentsOnCourses studentsOnCourses1 = new StudentsOnCourses(2L, 1L, 1L);
    private final StudentsOnCourses studentsOnCourses2 = new StudentsOnCourses(3L, 1L, 2L);

    private final String requestStudentsOnCourses = """
                            {
                            	"studentId": 1,
                                "courses": [1, 2]
                            }
                        """;

}
