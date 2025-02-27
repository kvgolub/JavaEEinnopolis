package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.StudentCourse;

import java.util.List;


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findStudentCourseByStudentId(Long studentId);
    List<StudentCourse> findStudentCourseByCourseId(Long courseId);

}
