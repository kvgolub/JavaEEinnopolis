package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.StudentController;
import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;
import ru.innopolis.service.StudentService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;


    @Override
    public ResponseEntity<StudentResponse> createStudent(StudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.createStudent(studentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

    @Override
    public ResponseEntity<Optional<StudentResponse>> findByIdStudent(Long studentId) {
        Optional<StudentResponse> studentResponse = studentService.findByIdStudent(studentId);

        return studentResponse.isPresent()
                ? ResponseEntity.ok().body(studentResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentResponse);
    }

    @Override
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        List<StudentResponse> studentsResponses = studentService.findAllStudents();

        return !studentsResponses.isEmpty()
                ? ResponseEntity.ok().body(studentsResponses)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<StudentResponse>> updateStudent(Long id, StudentRequest studentRequest) {
        Optional<StudentResponse> studentResponseNew = studentService.updateStudent(id, studentRequest);

        if (studentResponseNew.isPresent()) {
            return ResponseEntity.ok().body(studentResponseNew);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentResponseNew);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdStudent(Long studentId) {
        boolean studentNew = studentService.deleteByIdStudent(studentId);

        if (studentNew) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllStudents() {
        int students = studentService.deleteAllStudents();

        if (students > 0) {
            return ResponseEntity.ok().body(students);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
