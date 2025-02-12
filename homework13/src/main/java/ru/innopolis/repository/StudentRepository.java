package ru.innopolis.repository;

import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Student;

import java.util.List;


@Repository
public interface StudentRepository {

    int createStudent(Student student);
    List<Student> findByIdStudent(Long id);
    List<Student> findAllStudents();
    int updateStudent(Student student);
    int deleteByIdStudent(Long id);
    int deleteAllStudents();

}
