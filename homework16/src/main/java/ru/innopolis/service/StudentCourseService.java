package ru.innopolis.service;

import ru.innopolis.dto.studentCourse.*;


public interface StudentCourseService {

    OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest);
    AllStudentsOneCourseResponse findAllStudentsOneCourse(Long id);

    AllStudentsCertainAgeOneCourseResponse getAllStudentsCertainAgeOneCourse(Long id, Integer age);

}
