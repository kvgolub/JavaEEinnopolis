package ru.innopolis.service;

import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;


public interface StudentOnCourseService {

    StudentOnCourseResponse createStudentInManyCourses(StudentOnCourseRequest studentOnCourseRequest);

}
