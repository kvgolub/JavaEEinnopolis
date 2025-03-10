package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Course;

import java.util.List;
import java.util.Set;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByIdIn(Set<Long> id);

}
