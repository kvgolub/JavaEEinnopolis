package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.StudentCourseController;
import ru.innopolis.dto.studentCourse.*;
import ru.innopolis.service.StudentCourseService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class StudentCourseControllerImpl implements StudentCourseController {

    private final StudentCourseService studentCourseService;

    @Override
    public ResponseEntity<OneStudentManyCoursesResponse> createOneStudentManyCourses(OneStudentManyCoursesRequest oneStudentManyCoursesRequest) {
        OneStudentManyCoursesResponse oneStudentManyCoursesResponse = studentCourseService.createOneStudentManyCourses(oneStudentManyCoursesRequest);

        if (oneStudentManyCoursesResponse != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(oneStudentManyCoursesResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<ManyStudentsOneCourseResponse> findAllStudentsOneCourse(Long id) {
        ManyStudentsOneCourseResponse manyStudentsOneCourseResponse = studentCourseService.findAllStudentsOneCourse(id);

        if (manyStudentsOneCourseResponse != null) {
            return ResponseEntity.ok().body(manyStudentsOneCourseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @Override
    public ResponseEntity<ManyStudentsOneCourseResponse> findAllStudentsCertainAgeOneCourse(Integer age, Long id) {
        ManyStudentsOneCourseResponse manyStudentsOneCourseResponse = studentCourseService.findAllStudentsCertainAgeOneCourse(age, id);

        if (manyStudentsOneCourseResponse != null) {
            return ResponseEntity.ok().body(manyStudentsOneCourseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<List<OneStudentManyCoursesResponse>> findAllStudentsTwoOrMoreCourses(Long courseCount) {
        List<OneStudentManyCoursesResponse> oneStudentManyCoursesResponseList = studentCourseService.findAllStudentsTwoOrMoreCoursesResponse(courseCount);

        return !oneStudentManyCoursesResponseList.isEmpty()
                ? ResponseEntity.ok().body(oneStudentManyCoursesResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OneStudentManyCoursesResponse>> findStudentByNameAndCourseByName(String studentName, String courseName) {
        List<OneStudentManyCoursesResponse> oneStudentManyCoursesResponseList = studentCourseService.findStudentsByNameAndCoursesByName(studentName, courseName);

        return !oneStudentManyCoursesResponseList.isEmpty()
                ? ResponseEntity.ok().body(oneStudentManyCoursesResponseList)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OneStudentManyCoursesResponse>> findAllStudentsCertainAgeTwoOrMoreCourses(Integer age, Long courseCount) {
        List<OneStudentManyCoursesResponse> oneStudentManyCoursesResponseList = studentCourseService.findAllStudentsCertainAgeTwoOrMoreCourses(age, courseCount);

        return !oneStudentManyCoursesResponseList.isEmpty()
                ? ResponseEntity.ok().body(oneStudentManyCoursesResponseList)
                : ResponseEntity.noContent().build();
    }
}
