package ru.innopolis.repository;

import ru.innopolis.entity.StudentCourse;

import java.util.List;


public interface StudentCourseRepository {

    StudentCourse createStudentCourse(StudentCourse studentCourse);
    List<StudentCourse> findOneStudentAllCourses(Long studentId);
    List<StudentCourse> findAllStudentsOneCourse(Long courseId);
    List<StudentCourse> findAllStudentCourse();

}
