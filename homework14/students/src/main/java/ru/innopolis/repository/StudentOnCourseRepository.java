package ru.innopolis.repository;

import ru.innopolis.entity.StudentOnCourse;

import java.util.List;


public interface StudentOnCourseRepository {

    StudentOnCourse createStudentOnCourse(StudentOnCourse studentOnCourse);
    List<StudentOnCourse> findAllCoursesForStudent(Long studentId);

}
