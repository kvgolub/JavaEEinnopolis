package ru.innopolis.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Student;

import java.util.List;


@Repository
public interface StudentRepository {

    Student createStudent(Student student);
    Student findByIdStudent(Long id);
    List<Student> findAllStudents();
    Student updateStudent(Student student);
    int deleteByIdStudent(Long id);
    int deleteAllStudents();

}
