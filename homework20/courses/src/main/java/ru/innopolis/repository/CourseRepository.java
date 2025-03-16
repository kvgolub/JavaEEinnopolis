package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
