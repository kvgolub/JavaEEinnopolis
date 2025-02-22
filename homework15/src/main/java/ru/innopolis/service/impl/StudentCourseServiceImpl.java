package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.studentCourse.AllStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.impl.CourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentCourseRepositoryImpl;
import ru.innopolis.repository.impl.StudentRepositoryImpl;
import ru.innopolis.service.StudentCourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentRepositoryImpl studentRepository;
    //private final CoursesClientImpl coursesClient;
    private final CourseRepositoryImpl courseRepository;
    private final StudentCourseRepositoryImpl studentCourseRepository;


    public StudentCourseServiceImpl(StudentRepositoryImpl studentRepository, StudentCourseRepositoryImpl studentCourseRepository, CourseRepositoryImpl courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }


    @Override
    public OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest) {
        try {
            Student student = studentRepository.findByIdStudent(oneStudentManyCoursesRequest.getStudentId());
            List<StudentCourse> studentCourse = studentCourseRepository.findOneStudentAllCourses(oneStudentManyCoursesRequest.getStudentId());

            List<Course> courses = new ArrayList<>();
            oneStudentManyCoursesRequest.getCoursesId().forEach(aLong -> {
                if (studentCourse.stream().noneMatch(studentCourse1 ->
                        Objects.equals(studentCourse1.getCourseId(), aLong)
                )) {
                    courses.add(courseRepository.findByIdCourse(aLong));
                }
            });

            courses.stream().toList().forEach(aLong ->
                    studentCourseRepository.createStudentCourse(
                            new StudentCourse(
                                    null,
                                    oneStudentManyCoursesRequest.getStudentId(),
                                    aLong.getId()
                            )
                    )
            );

            List<CourseResponse> courseList = new ArrayList<>();
            courses.forEach(course -> courseList.add(
                    new CourseResponse(
                            course.getId(),
                            course.getName(),
                            course.getDate(),
                            course.getActive()
                    )
            ));

            return new OneStudentManyCoursesResponse(
                    student.getId(),
                    student.getSurname(),
                    student.getName(),
                    student.getPatronymic(),
                    student.getEmail(),
                    courseList
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AllStudentsOneCourseResponse findAllStudentsOneCourse(Long id) {
        try {
            Course course = courseRepository.findByIdCourse(id);
            List<StudentCourse> studentCourses = studentCourseRepository.findAllStudentsOneCourse(id);

            List<Student> students = new ArrayList<>();
            studentCourses.forEach(aLong -> {
                /*if (studentCourses.stream().noneMatch(studentCourse1 ->
                        Objects.equals(studentCourse1.getStudentId(), aLong.getStudentId())
                )) {*/
                    students.add(studentRepository.findByIdStudent(aLong.getStudentId()));
                //}
            });

            List<StudentResponse> studentList = new ArrayList<>();
            students.forEach(student -> studentList.add(
                    new StudentResponse(
                            student.getId(),
                            student.getSurname(),
                            student.getName(),
                            student.getPatronymic(),
                            student.getEmail()
                    )
            ));

            return new AllStudentsOneCourseResponse(
                    course.getId(),
                    course.getName(),
                    course.getDate(),
                    course.getActive(),
                    studentList
            );
        } catch (Exception e) {
            return null;
        }
    }

}
