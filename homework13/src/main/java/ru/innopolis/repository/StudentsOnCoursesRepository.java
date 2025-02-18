package ru.innopolis.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.entity.StudentsOnCourses;

import java.util.List;


@Repository
public interface StudentsOnCoursesRepository {

    StudentsOnCourses createStudentsOnCourses(Long id, Long studentId, Long courseId);
    List<StudentsOnCourses> findByIdStudentsOnCourses(Long id);

}
