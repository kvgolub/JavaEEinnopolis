package ru.innopolis.repository;

import ru.innopolis.entity.Course;

import java.util.List;


public interface CourseRepository {

    Course createCourse(Course course);
    Course findByIdCourse(Long id);
    List<Course> findAllCourses();
    Course updateCourse(Course course);
    int deleteByIdCourse(Long id);
    int deleteAllCourses();

}
