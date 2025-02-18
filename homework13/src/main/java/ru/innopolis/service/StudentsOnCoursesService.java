package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.StudentsOnCourses;

import java.util.List;


@Service
public interface StudentsOnCoursesService {

    List<StudentsOnCourses> createStudentsOnCourses(Long studentId, List<Long> courses);

}
