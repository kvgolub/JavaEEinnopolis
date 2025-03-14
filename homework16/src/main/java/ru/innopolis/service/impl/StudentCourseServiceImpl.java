package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.dto.studentCourse.*;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;
import ru.innopolis.entity.StudentCourse;
import ru.innopolis.repository.CourseRepository;
import ru.innopolis.repository.StudentCourseRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.StudentCourseService;

import java.util.*;


@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;


    public StudentCourseServiceImpl(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }


    @Override
    public OneStudentManyCoursesResponse createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest) {
        try {
            Student student = studentRepository.findById(oneStudentManyCoursesRequest.getStudentId()).orElseThrow();
            List<StudentCourse> studentCourse = studentCourseRepository.findStudentCourseByStudentId(oneStudentManyCoursesRequest.getStudentId());

            List<Course> courses = new ArrayList<>();
            oneStudentManyCoursesRequest.getCoursesId().forEach(aLong -> {
                if (studentCourse.stream().noneMatch(studentCourse1 ->
                        Objects.equals(studentCourse1.getCourseId(), aLong)
                )) {
                    courses.add(courseRepository.findById(aLong).orElseThrow());
                }
            });

            courses.stream().toList().forEach(aLong ->
                    studentCourseRepository.save(
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
                    student.getAge(),
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
            Course course = courseRepository.findById(id).orElseThrow();
            List<StudentCourse> studentCourses = studentCourseRepository.findStudentCourseByCourseId(id);

            List<Student> students = new ArrayList<>();
            studentCourses.forEach(aLong -> students.add(studentRepository.findById(aLong.getStudentId()).orElseThrow()));

            List<StudentResponse> studentList = new ArrayList<>();
            students.forEach(student -> studentList.add(
                    new StudentResponse(
                            student.getId(),
                            student.getSurname(),
                            student.getName(),
                            student.getPatronymic(),
                            student.getAge(),
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

    @Override
    public AllStudentsCertainAgeOneCourseResponse getAllStudentsCertainAgeOneCourse(Long id, Integer age) {
        try {
            List<Student> getStudentCertainAge = studentRepository.queryStudentsByAgeGreaterThanEqual(age);
            AllStudentsOneCourseResponse allStudentsOneCourseResponse = findAllStudentsOneCourse(id);

            List<StudentResponse> studentCertainAge = new ArrayList<>();
            allStudentsOneCourseResponse.getStudents().forEach(studentResponse -> {
                if(getStudentCertainAge.stream().anyMatch(student -> Objects.equals(student.getId(), studentResponse.getId()))) {
                    studentCertainAge.add(studentResponse);
                }
            });

            return new AllStudentsCertainAgeOneCourseResponse(
                    allStudentsOneCourseResponse.getId(),
                    allStudentsOneCourseResponse.getName(),
                    allStudentsOneCourseResponse.getDate(),
                    allStudentsOneCourseResponse.getActive(),
                    studentCertainAge
            );
        } catch (Exception e) {
            return null;
        }
    }

}
