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
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student studentRequest) {
        Student studentNew = studentService.createStudent(studentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(studentNew);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findByIdStudent(@Valid @PathVariable("id") Long studentId) {
        Student student = studentService.findByIdStudent(studentId);

        return student != null
                ? ResponseEntity.ok().body(student)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentService.findAllStudents();

        return !students.isEmpty()
                ? ResponseEntity.ok().body(students)
                : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @PathVariable Long id, @Valid @RequestBody Student studentRequest) {
        Student studentUpdate = studentService.updateStudent(id, studentRequest);

        if (studentUpdate != null) {
            return ResponseEntity.ok().body(studentUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteByIdStudent(@Valid @PathVariable("id") Long studentId) {
        boolean student = studentService.deleteByIdStudent(studentId);

        if (student) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
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
