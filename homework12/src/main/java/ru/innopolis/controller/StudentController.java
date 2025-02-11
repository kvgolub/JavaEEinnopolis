package ru.innopolis.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.StudentRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student request) {
        Optional<Student> student = studentRepository.findById(request.getId());

        if (student.isEmpty()) {
            Student studentNew = studentRepository.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentNew);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findByIdStudent(@PathVariable("id") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        return student.isPresent()
                ? ResponseEntity.of(student)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentRepository.findAll();

        return !students.isEmpty()
                ? ResponseEntity.ok().body(students)
                : ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student request) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            Student studentNew = student.get();
            studentNew.setSurname(request.getSurname());
            studentNew.setName(request.getName());
            studentNew.setPatronymic(request.getPatronymic());
            studentNew.setEmail(request.getEmail());
            studentNew.setCourse(request.getCourse());

            return ResponseEntity.ok().body(studentRepository.save(studentNew));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteByIdStudent(@PathVariable("id") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isPresent()) {
            studentRepository.deleteById(student.get().getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Student> deleteAllStudents() {
        List<Student> students = studentRepository.findAll();

        if (!students.isEmpty()) {
            studentRepository.deleteAll();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
