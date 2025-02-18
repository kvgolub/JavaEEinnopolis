package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;


@RequestMapping("/api/v1/students_on_courses")
public interface StudentOnCourseController {

    @PostMapping
    ResponseEntity<StudentOnCourseResponse> createStudentInManyCourses(@Valid @RequestBody StudentOnCourseRequest studentOnCourseRequest);

}
