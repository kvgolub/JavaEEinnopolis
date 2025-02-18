package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.repository.impl.StudentsOnCoursesRepositoryImpl;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentsOnCoursesServiceImpl implements StudentsOnCoursesService {

    private final StudentsOnCoursesRepositoryImpl studentsOnCoursesRepository;

    public StudentsOnCoursesServiceImpl(StudentsOnCoursesRepositoryImpl studentsOnCoursesRepository) {
        this.studentsOnCoursesRepository = studentsOnCoursesRepository;
    }


    @Override
    public List<StudentsOnCourses> createStudentsOnCourses(Long studentId, List<Long> courses) {
        List<StudentsOnCourses> studentsOnCoursesList = new ArrayList<>();
        courses.forEach(aLong -> studentsOnCoursesList.add(studentsOnCoursesRepository.createStudentsOnCourses(studentId + aLong, studentId, aLong)));

        return studentsOnCoursesList;
    }

}
