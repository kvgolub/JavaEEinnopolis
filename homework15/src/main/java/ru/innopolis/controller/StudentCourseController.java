package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.studentCourse.AllStudentsOneCourseResponse;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesRequest;
import ru.innopolis.dto.studentCourse.OneStudentManyCoursesResponse;


@RequestMapping("/api/v1/students_on_courses")
public interface StudentCourseController {

    @PostMapping
    ResponseEntity<OneStudentManyCoursesResponse> createOneStudentManyCourses(@Valid @RequestBody OneStudentManyCoursesRequest oneStudentManyCoursesRequest);

    @GetMapping("/{id}")
    ResponseEntity<AllStudentsOneCourseResponse> findAllStudentsOneCourse(@Valid @PathVariable Long id);

}
