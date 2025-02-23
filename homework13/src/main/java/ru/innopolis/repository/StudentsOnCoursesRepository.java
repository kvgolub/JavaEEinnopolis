package ru.innopolis.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.entity.StudentsOnCourses;

import java.util.List;


@Repository
public interface StudentsOnCoursesRepository {

    StudentsOnCourses createStudentsOnCourses(StudentsOnCourses studentsOnCourses);
    List<StudentsOnCourses> findByIdStudentsOnCourses(Long id);

}
