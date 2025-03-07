package ru.innopolis.service.impl;

import org.springframework.stereotype.Service;
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

            return new OneStudentManyCoursesResponse(
                    student.getId(),
                    student.getSurname(),
                    student.getName(),
                    student.getPatronymic(),
                    student.getAge(),
                    student.getEmail(),
                    courses
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ManyStudentsOneCourseResponse findAllStudentsOneCourse(Long id) {
        try {
            Course course = courseRepository.findById(id).orElseThrow();
            List<StudentCourse> studentCourses = studentCourseRepository.findStudentCourseByCourseId(id);

            List<Student> students = new ArrayList<>();
            studentCourses.forEach(aLong -> students.add(studentRepository.findById(aLong.getStudentId()).orElseThrow()));

            return new ManyStudentsOneCourseResponse(
                    course.getId(),
                    course.getName(),
                    course.getDate(),
                    course.getActive(),
                    students
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ManyStudentsOneCourseResponse findAllStudentsCertainAgeOneCourse(Integer age, Long id) {
        try {
            List<Student> getStudentCertainAge = studentRepository.queryStudentsByAgeGreaterThanEqual(age);
            ManyStudentsOneCourseResponse manyStudentsOneCourseResponse = findAllStudentsOneCourse(id);

            return new ManyStudentsOneCourseResponse(
                    manyStudentsOneCourseResponse.getId(),
                    manyStudentsOneCourseResponse.getName(),
                    manyStudentsOneCourseResponse.getDate(),
                    manyStudentsOneCourseResponse.getActive(),
                    getStudentCertainAge
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OneStudentManyCoursesResponse> findAllStudentsTwoOrMoreCoursesResponse(Long courseCount) {
        try {
            List<StudentCourse> studentCourseList = studentCourseRepository.findAllStudentsTwoOrMoreCourses(courseCount);

            return getOneStudentManyCoursesList(studentCourseList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OneStudentManyCoursesResponse> findStudentsByNameAndCoursesByName(String studentName, String courseName) {
        try {
            List<StudentCourse> studentCourseList = studentCourseRepository.findStudentByNameAndCourseByName(studentName, courseName);

            return getOneStudentManyCoursesList(studentCourseList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OneStudentManyCoursesResponse> findAllStudentsCertainAgeTwoOrMoreCourses(Integer age, Long courseCount) {
        try {
            List<StudentCourse> studentCourseList = studentCourseRepository.findAllStudentsCertainAgeTwoOrMoreCourses(age, courseCount);

            return getOneStudentManyCoursesList(studentCourseList);
        } catch (Exception e) {
            return null;
        }
    }


    private List<OneStudentManyCoursesResponse> getOneStudentManyCoursesList(List<StudentCourse> studentCourseList) {
        Set<Long> studentsID = new HashSet<>();
        studentCourseList.forEach(studentCourse -> studentsID.add(studentCourse.getStudentId()));
        List<Student> studentList = studentRepository.findStudentsByIdIn(studentsID);

        List<OneStudentManyCoursesResponse> oneStudentManyCoursesResponses = new ArrayList<>();
        studentList.forEach(student -> {
            OneStudentManyCoursesResponse oneStudentManyCoursesResponse = new OneStudentManyCoursesResponse();

            Set<Long> coursesID = new HashSet<>();
            studentCourseList.forEach(studentCourse -> coursesID.add(studentCourse.getCourseId()));

            oneStudentManyCoursesResponse.setId(student.getId());
            oneStudentManyCoursesResponse.setSurname(student.getSurname());
            oneStudentManyCoursesResponse.setName(student.getName());
            oneStudentManyCoursesResponse.setPatronymic(student.getPatronymic());
            oneStudentManyCoursesResponse.setAge(student.getAge());
            oneStudentManyCoursesResponse.setEmail(student.getEmail());
            oneStudentManyCoursesResponse.setCourses(courseRepository.findAllById(coursesID));

            oneStudentManyCoursesResponses.add(oneStudentManyCoursesResponse);
        });

        return oneStudentManyCoursesResponses;
    }

}
