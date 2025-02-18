package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentOnCourse;
import ru.innopolis.repository.impl.StudentOnCourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.StudentOnCourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class StudentOnCourseServiceImpl implements StudentOnCourseService {

    private final StudentRepositoryImpl studentRepository;
    private final CoursesClientImpl coursesClient;
    private final StudentOnCourseRepositoryImpl studentsOnCoursesRepository;


    public StudentOnCourseServiceImpl(StudentRepositoryImpl studentRepository, StudentOnCourseRepositoryImpl studentsOnCoursesRepository, CoursesClientImpl coursesClient) {
        this.studentRepository = studentRepository;
        this.coursesClient = coursesClient;
        this.studentsOnCoursesRepository = studentsOnCoursesRepository;
    }


    @Override
    public StudentOnCourseResponse createStudentInManyCourses(StudentOnCourseRequest studentOnCourseRequest) {
        try {
            Student student = studentRepository.findByIdStudent(studentOnCourseRequest.getStudentId());
            List<StudentOnCourse> studentOnCourse = studentsOnCoursesRepository.findAllCoursesForStudent(studentOnCourseRequest.getStudentId());

            List<CourseResponse> coursesResponse = new ArrayList<>();
            studentOnCourseRequest.getCoursesId().forEach(aLong -> {
                if (studentOnCourse.stream().noneMatch(studentOnCourse1 ->
                        Objects.equals(studentOnCourse1.getCourseId(), aLong)
                )) {
                    if (coursesClient.getCourse(aLong).getActive()) {
                        coursesResponse.add(coursesClient.getCourse(aLong));
                    }
                }
            });

            coursesResponse.stream().toList().forEach(aLong ->
                    studentsOnCoursesRepository.createStudentOnCourse(
                            new StudentOnCourse(
                                    null,
                                    studentOnCourseRequest.getStudentId(),
                                    aLong.getId()
                            )
                    )
            );

            return new StudentOnCourseResponse(
                    student.getId(),
                    student.getSurname(),
                    student.getName(),
                    student.getPatronymic(),
                    student.getEmail(),
                    coursesResponse
            );
        } catch (Exception e) {
            return null;
        }
    }

}
