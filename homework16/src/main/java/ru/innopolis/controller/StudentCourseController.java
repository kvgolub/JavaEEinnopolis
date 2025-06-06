package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.studentCourse.*;


@RequestMapping("/api/v1/students_on_courses")
public interface StudentCourseController {

    @PostMapping
    ResponseEntity<OneStudentManyCoursesResponse> createOneStudentManyCourses(@Valid @RequestBody OneStudentManyCoursesRequest oneStudentManyCoursesRequest);

    @GetMapping("/{id}")
    ResponseEntity<AllStudentsOneCourseResponse> findAllStudentsOneCourse(@Valid @PathVariable Long id);

    @GetMapping("/age_more_than")
    ResponseEntity<AllStudentsCertainAgeOneCourseResponse> getAllStudentsCertainAgeOneCourse(@Valid @RequestParam(name = "id") Long id, @Valid @RequestParam(name = "age") Integer age);

}
