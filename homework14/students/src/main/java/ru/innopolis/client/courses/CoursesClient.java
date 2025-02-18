package ru.innopolis.client.courses;

import org.springframework.stereotype.Component;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;


@Component
public interface CoursesClient {

    CourseResponse getCourse(Long id);
    List<CourseResponse> getCourses();

}
