package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.repository.impl.CourseRepositoryImpl;
import ru.innopolis.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryImpl courseRepository;

    public CourseServiceImpl(CourseRepositoryImpl courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course courseInDb = new Course(
                null,
                courseRequest.getName(),
                courseRequest.getDate(),
                courseRequest.getActive()
        );
        Course courseFromDb = courseRepository.createCourse(courseInDb);

        return new CourseResponse(
                courseFromDb.getId(),
                courseFromDb.getName(),
                courseFromDb.getDate(),
                courseFromDb.getActive()
        );
    }

    @Override
    public Optional<CourseResponse> findByIdCourse(Long id) {
        try {
            Course courseFromDb = courseRepository.findByIdCourse(id);

            return Optional.of(
                    new CourseResponse(
                            courseFromDb.getId(),
                            courseFromDb.getName(),
                            courseFromDb.getDate(),
                            courseFromDb.getActive()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<CourseResponse> findAllCourses() {
        List<Course> courseFromDb = courseRepository.findAllCourses();
        List<CourseResponse> response = new ArrayList<>();
        courseFromDb.forEach(course -> response.add(
                new CourseResponse(
                        course.getId(),
                        course.getName(),
                        course.getDate(),
                        course.getActive()
                )
        ));

        return response;
    }

    @Override
    public Optional<CourseResponse> updateCourse(Long id, CourseRequest courseRequest) {
        try {
            Course getCourse = courseRepository.findByIdCourse(id);

            if (getCourse.getId() != null) {
                Course courseNew = new Course(
                        id,
                        courseRequest.getName(),
                        courseRequest.getDate(),
                        courseRequest.getActive()
                );

                Course courseFromDb = courseRepository.updateCourse(courseNew);

                return Optional.of(
                        new CourseResponse(
                                courseFromDb.getId(),
                                courseFromDb.getName(),
                                courseFromDb.getDate(),
                                courseFromDb.getActive()
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteByIdCourse(Long id) {
        return courseRepository.deleteByIdCourse(id) == 1;
    }

    @Override
    public int deleteAllCourses() {
        return courseRepository.deleteAllCourses();
    }
}
