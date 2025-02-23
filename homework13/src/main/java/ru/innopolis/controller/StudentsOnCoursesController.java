package ru.innopolis.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.RequestRegistrationStudent;
import ru.innopolis.entity.StudentsOnCourses;
import ru.innopolis.service.StudentsOnCoursesService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students_on_courses")
public class StudentsOnCoursesController {

    private final StudentsOnCoursesService studentsOnCoursesService;

    @PostMapping
    public ResponseEntity<List<StudentsOnCourses>> createStudentsOnCourses(@Valid @RequestBody RequestRegistrationStudent requestRegistrationStudent) {
        List<StudentsOnCourses> studentsOnCoursesNew = studentsOnCoursesService.createStudentsOnCourses(requestRegistrationStudent.getStudentId(), requestRegistrationStudent.getCourses());

        if (!studentsOnCoursesNew.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentsOnCoursesNew);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



}
