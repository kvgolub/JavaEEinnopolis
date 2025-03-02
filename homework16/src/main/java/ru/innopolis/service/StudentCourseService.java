package ru.innopolis.service;

import ru.innopolis.dto.studentCourse.*;


public interface StudentCourseService {

    OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest);
    AllStudentsOneCourseResponse findAllStudentsOneCourse(Long id);

    Over30yoAllStudentsOneCourseResponse getOver30yoAllStudentsOneCourse(Long id, Integer age);

}
