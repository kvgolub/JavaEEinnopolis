package ru.innopolis.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;

import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/student")
public interface StudentController {

    @PostMapping
    ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest);

    @GetMapping("/{id}")
    ResponseEntity<Optional<StudentResponse>> findByIdStudent(@Valid @PathVariable("id") Long studentId);

    @GetMapping("/list")
    ResponseEntity<List<StudentResponse>> findAllStudents();

    @PutMapping("/{id}")
    ResponseEntity<Optional<StudentResponse>> updateStudent(@Valid @PathVariable Long id, @Valid @RequestBody StudentRequest studentRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteByIdStudent(@Valid @PathVariable("id") Long studentId);

    @DeleteMapping("/all")
    ResponseEntity<Integer> deleteAllStudents();


    @GetMapping("/over30yo/{age}")
    ResponseEntity<List<StudentResponse>> getStudentOver30yearsOld(@Valid @PathVariable("age") Integer age);

}
