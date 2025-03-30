package ru.innopolis.service;

import ru.innopolis.dto.account.AccountResponse;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.studentCourse.ManyStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;

import java.util.List;


public interface AccountService {

    AccountResponse findOneStudentAllCourses(String username);

}
