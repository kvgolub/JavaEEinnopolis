package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Student;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> queryStudentsByAgeGreaterThanEqual(Integer age);

}
