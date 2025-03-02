package ru.innopolis.service;

import ru.innopolis.dto.student.StudentRequest;
import ru.innopolis.dto.student.StudentResponse;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    StudentResponse createStudent(StudentRequest studentRequest);
    Optional<StudentResponse> findByIdStudent(Long id);
    List<StudentResponse> findAllStudents();
    Optional<StudentResponse> updateStudent(Long id, StudentRequest studentRequest);
    boolean deleteByIdStudent(Long id);
    int deleteAllStudents();

    List<StudentResponse> getStudentOver30yearsOld(Integer age);

}
