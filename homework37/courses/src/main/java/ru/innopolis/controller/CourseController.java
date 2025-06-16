package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RequestMapping("/api/v1/course")
public interface CourseController {

    @PostMapping
    ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<CourseResponse>> findByIdCourse(@PathVariable("id") Long courseId);

    @GetMapping("/list")
    ResponseEntity<List<CourseResponse>> findAllCourses();

    @PutMapping("/{id}")
    ResponseEntity<Optional<CourseResponse>> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdCourse(@PathVariable("id") Long courseId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllCourses();


    @PostMapping("/definite_courses")
    ResponseEntity<List<CourseResponse>> findCoursesByIds(@Valid @RequestBody Set<Long> ids);
}
