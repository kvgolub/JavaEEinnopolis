package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Student;

import java.util.List;
import java.util.Set;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> queryStudentsByAgeGreaterThanEqual(Integer age);
    List<Student> findStudentsByIdIn(Set<Long> id);

    Student findStudentByEmail(String username);

}
