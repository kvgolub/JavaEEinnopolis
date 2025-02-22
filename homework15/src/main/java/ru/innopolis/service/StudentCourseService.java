package ru.innopolis.service;

import ru.innopolis.dto.studentCourse.AllStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;


public interface StudentCourseService {

    OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest);
    AllStudentsOneCourseResponse findAllStudentsOneCourse(Long id);

}
