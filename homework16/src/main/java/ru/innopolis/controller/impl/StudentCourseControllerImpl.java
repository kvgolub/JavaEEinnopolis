package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.StudentCourseController;
import ru.innopolis.dto.studentCourse.*;
import ru.innopolis.service.StudentCourseService;


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
    public ResponseEntity<AllStudentsOneCourseResponse> findAllStudentsOneCourse(Long id) {
        AllStudentsOneCourseResponse allStudentsOneCourseResponse = studentCourseService.findAllStudentsOneCourse(id);

        if (allStudentsOneCourseResponse != null) {
            return ResponseEntity.ok().body(allStudentsOneCourseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @Override
    public ResponseEntity<Over30yoAllStudentsOneCourseResponse> getOver30yoAllStudentsOneCourse(Long id, Integer age) {
        Over30yoAllStudentsOneCourseResponse over30yoAllStudentsOneCourseResponse = studentCourseService.getOver30yoAllStudentsOneCourse(id, age);

        if (over30yoAllStudentsOneCourseResponse != null) {
            return ResponseEntity.ok().body(over30yoAllStudentsOneCourseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
