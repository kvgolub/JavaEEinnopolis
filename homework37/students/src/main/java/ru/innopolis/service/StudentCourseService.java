package ru.innopolis.service;

import ru.innopolis.dto.studentCourse.*;

import java.util.List;


public interface StudentCourseService {

    OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest);
    ManyStudentsOneCourseResponse findAllStudentsOneCourse(Long id);

    ManyStudentsOneCourseResponse findAllStudentsCertainAgeOneCourse(Integer age, Long id);

    List<OneStudentManyCoursesResponse> findAllStudentsTwoOrMoreCoursesResponse(Long courseCount);
    List<OneStudentManyCoursesResponse> findStudentsByNameAndCoursesByName(String studentName, String courseName);
    List<OneStudentManyCoursesResponse> findAllStudentsCertainAgeTwoOrMoreCourses(Integer age, Long courseCount);
}
