package ru.innopolis.controller;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public CourseControllerTest() throws ParseException {
    }


    @Test
    void createCourseControllerTest() throws Exception {
        Mockito.when(courseService.createCourse(Mockito.any(CourseRequest.class))).thenReturn(courseResponse);

        mvc.perform(post("/api/v1/course")
                        .contentType("application/json")
                        .content(courseRequestBody)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void findByIdCourseControllerTest() throws Exception {
        Mockito.when(courseService.findByIdCourse(Mockito.any(Long.class))).thenReturn(Optional.of(courseResponse));

        mvc.perform(get("/api/v1/course/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(courseResponseJson));
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
                .andExpect(content().json(courseResponseNewJson));
    }

    @Test
    void deleteByIdCourseControllerTest() throws Exception {
        Mockito.when(courseService.deleteByIdCourse(Mockito.any(Long.class))).thenReturn(true);

        mvc.perform(delete("/api/v1/course/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllCoursesControllerTest() throws Exception {
        Mockito.when(courseService.deleteAllCourses()).thenReturn(1);

        mvc.perform(delete("/api/v1/course/all"))
                .andExpect(status().isOk());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final CourseResponse courseResponse = new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);
    private final String courseRequestBody = """
                            {
                            	"name": "Программирование на языке JAVA",
                                "date": "2024-03-01T10:00:00.000+02:00",
                                "active": true
                            }
                        """;

    // find
    private final String courseResponseJson = """
                            {
                            	"id": 1,
                            	"name": "Программирование на языке JAVA",
                                "date": "2024-03-01T08:00:00.000+00:00",
                                "active": true
                            }
                        """;

    // findAll
    private final List<CourseResponse> coursesResponseAll = List.of(
            new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true),
            new CourseResponse(2L, "Программирование на языке PYTHON", sdf.parse("2024-04-01T12:00:00.000+02:00"), true),
            new CourseResponse(3L, "Программирование на языке C++", sdf.parse("2024-03-15T11:00:00.000+02:00"), true)
    );

    // update
    private final CourseResponse courseResponseNew = new CourseResponse(1L, "Программирование на языке GO", sdf.parse("2024-03-20T10:30:00.000+02:00"), true);
    private final String courseRequestNewBody = """
                            {
                                "name": "Программирование на языке GO",
                                "date": "2024-03-20T10:30:00.000+02:00",
                                "active": true
                            }
                        """;
    private final String courseResponseNewJson = """
                            {
                                "id": 1,
                                "name": "Программирование на языке GO",
                                "date": "2024-03-20T08:30:00.000+00:00",
                                "active": true
                            }
                        """;

}
