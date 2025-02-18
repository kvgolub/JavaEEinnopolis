import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.CourseControllerImpl;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.service.impl.CourseServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ContextConfiguration(classes = {CourseControllerImpl.class})
public class CourseControllerTest {

    @MockitoBean
    private CourseServiceImpl courseService;

    @Autowired
    private MockMvc mvc;


    @Test
    void createCourseControllerTest() throws Exception {
        Mockito.when(courseService.createCourse(courseRequest)).thenReturn(courseResponse);

        mvc.perform(post("/api/v1/course")
                        .contentType("application/json")
                        .content(courseRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findByIdCourseControllerTest() throws Exception {
        Mockito.when(courseService.findByIdCourse(1L)).thenReturn(Optional.of(courseResponse));

        mvc.perform(get("/api/v1/course/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(courseResponseBody));
    }

    @Test
    void findAllCoursesControllerTest() throws Exception {
        Mockito.when(courseService.findAllCourses()).thenReturn(coursesResponseAll);

        mvc.perform(get("/api/v1/course/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateCourseControllerTest() throws Exception {
        Mockito.when(courseService.updateCourse(Mockito.any(Long.class), Mockito.any(CourseRequest.class))).thenReturn(Optional.of(courseResponseNew));

        mvc.perform(put("/api/v1/course/1")
                        .contentType("application/json")
                        .content(courseRequestNewBody)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(courseResponseNewBody));
    }

    @Test
    void deleteByIdCourseControllerTest() throws Exception {
        Mockito.when(courseService.deleteByIdCourse(1L)).thenReturn(true);

        mvc.perform(delete("/api/v1/course/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllCoursesControllerTest() throws Exception {
        Mockito.when(courseService.deleteAllCourses()).thenReturn(1);

        mvc.perform(delete("/api/v1/course/all"))
                .andExpect(status().isOk());
    }

    private final CourseRequest courseRequest = new CourseRequest("Программирование на языке JAVA", "2024-03-01 10:00:00", true);
    private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true);

    private final String courseRequestBody = """
                            {
                            	"name": "Программирование на языке JAVA",
                                "date": "2024-03-01 10:00:00",
                                "active": true
                            }
                        """;
    private final String courseResponseBody = """
                            {
                            	"id": 1,
                            	"name": "Программирование на языке JAVA",
                                "date": "2024-03-01 10:00:00",
                                "active": true
                            }
                        """;

    private final List<CourseResponse> coursesResponseAll = List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", "2024-03-01 10:00:00", true),
            new CourseResponse(2L, "Программирование на языке PYTHON", "2024-04-01 12:00:00", true),
            new CourseResponse(3L, "Программирование на языке C++", "2024-03-15 11:00:00", true)
    );

    private final CourseResponse courseResponseNew = new CourseResponse(1L, "Программирование на языке GO", "2024-03-20 10:30:00", true);

    private final String courseRequestNewBody = """
                            {
                                "name": "Программирование на языке GO",
                                "date": "2024-03-20 10:30:00",
                                "active": true
                            }
                        """;
    private final String courseResponseNewBody = """
                            {
                                "id": 1,
                                "name": "Программирование на языке GO",
                                "date": "2024-03-20 10:30:00",
                                "active": true
                            }
                        """;

}
