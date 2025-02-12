package ru.innopolis.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.Student;
import ru.innopolis.service.StudentService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Boolean> createStudent(@Valid @RequestBody Student request) {
        boolean studentNew = studentService.createStudent(request);

        if (studentNew) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findByIdStudent(@Valid @PathVariable("id") Long studentId) {
        Student student = studentService.findByIdStudent(studentId);

        return student != null
                ? ResponseEntity.ok().body(student)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentService.findAllStudents();

        return !students.isEmpty()
                ? ResponseEntity.ok().body(students)
                : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateStudent(@Valid @PathVariable Long id, @Valid @RequestBody Student request) {
        boolean studentNew = studentService.updateStudent(id, request);

        if (studentNew) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteByIdStudent(@Valid @PathVariable("id") Long studentId) {
        boolean studentNew = studentService.deleteByIdStudent(studentId);

        if (studentNew) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Integer> deleteAllStudents() {
        int studentNew = studentService.deleteAllStudents();

        if (studentNew > 0) {
            return ResponseEntity.ok().body(studentNew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
