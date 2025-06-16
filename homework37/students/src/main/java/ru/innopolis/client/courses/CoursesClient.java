package ru.innopolis.client.courses;

import org.springframework.stereotype.Component;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;
import java.util.Set;


@Component
public interface CoursesClient {

    CourseResponse getOneCourse(Long id);
    List<CourseResponse> getAllCourses();
    List<CourseResponse> getListCoursesByIds(Set<Long> ids);
}
