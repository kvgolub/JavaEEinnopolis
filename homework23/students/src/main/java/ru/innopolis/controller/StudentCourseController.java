package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.studentCourse.*;

import java.util.List;


@RequestMapping("/api/v1/students_on_courses")
public interface StudentCourseController {

    @PostMapping()
    ResponseEntity<OneStudentManyCoursesResponse> createOneStudentManyCourses(@Valid @RequestBody OneStudentManyCoursesRequest oneStudentManyCoursesRequest);

    @GetMapping("/{id}")
    ResponseEntity<ManyStudentsOneCourseResponse> findAllStudentsOneCourse(@Valid @PathVariable Long id);

    @GetMapping("/age_more_than")
    ResponseEntity<ManyStudentsOneCourseResponse> findAllStudentsCertainAgeOneCourse(@Valid @RequestParam(name = "age") Integer age, @Valid @RequestParam(name = "id") Long id);

    @GetMapping("/count/{courseCount}")
    ResponseEntity<List<OneStudentManyCoursesResponse>> findAllStudentsTwoOrMoreCourses(@PathVariable Long courseCount);

    @GetMapping("/search_by_name")
    ResponseEntity<List<OneStudentManyCoursesResponse>> findStudentByNameAndCourseByName(@Valid @RequestParam(name = "studentName") String studentName, @Valid @RequestParam(name = "courseName") String courseName);

    @GetMapping("/age_more_than_and_count")
    ResponseEntity<List<OneStudentManyCoursesResponse>> findAllStudentsCertainAgeTwoOrMoreCourses(@Valid @RequestParam(name = "age") Integer age, @Valid @RequestParam(name = "courseCount") Long courseCount);

}
