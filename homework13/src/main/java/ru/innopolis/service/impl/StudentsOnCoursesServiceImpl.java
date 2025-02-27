package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.repository.impl.StudentsOnCoursesRepositoryImpl;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentsOnCoursesServiceImpl implements StudentsOnCoursesService {

    private final StudentRepositoryImpl studentRepository;
    private final StudentsOnCoursesRepositoryImpl studentsOnCoursesRepository;

    public StudentsOnCoursesServiceImpl(StudentRepositoryImpl studentRepository, StudentsOnCoursesRepositoryImpl studentsOnCoursesRepository) {
        this.studentRepository = studentRepository;
        this.studentsOnCoursesRepository = studentsOnCoursesRepository;
    }


    @Override
    public List<StudentsOnCourses> createStudentsOnCourses(Long studentId, List<Long> courses) {
        try {
            Student getStudent = studentRepository.findByIdStudent(studentId);

            StudentsOnCourses studentsOnCourses = new StudentsOnCourses();
            List<StudentsOnCourses> studentsOnCoursesList = new ArrayList<>();

            courses.forEach(aLong -> {
                studentsOnCourses.setStudentId(getStudent.getId());
                studentsOnCourses.setCourseId(aLong);

                studentsOnCoursesList.add(
                        studentsOnCoursesRepository.createStudentsOnCourses(studentsOnCourses)
                );
            });

            return studentsOnCoursesList;
        } catch (Exception e) {
            return  List.of();
        }
    }

}
