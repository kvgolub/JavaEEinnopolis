package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Student;

import java.util.List;


@Service
public interface StudentService {

    Student createStudent(Student student);
    Student findByIdStudent(Long id);
    List<Student> findAllStudents();
    Student updateStudent(Long id, Student student);
    boolean deleteByIdStudent(Long id);
    int deleteAllStudents();

}
