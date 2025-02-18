package ru.innopolis.repository;

import ru.innopolis.entity.Student;

import java.util.List;


public interface StudentRepository {

    Student createStudent(Student student);
    Student findByIdStudent(Long id);
    List<Student> findAllStudents();
    Student updateStudent(Student student);
    int deleteByIdStudent(Long id);
    int deleteAllStudents();

}
