package ru.innopolis.service;

import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;
import java.util.Optional;


public interface CourseService {

    CourseResponse createCourse(CourseRequest courseRequest);
    Optional<CourseResponse> findByIdCourse(Long id);
    List<CourseResponse> findAllCourses();
    Optional<CourseResponse> updateCourse(Long id, CourseRequest courseRequest);
    boolean deleteByIdCourse(Long id);
    int deleteAllCourses();

}
