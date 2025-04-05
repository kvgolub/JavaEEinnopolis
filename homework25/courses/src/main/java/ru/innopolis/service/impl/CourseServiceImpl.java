package ru.innopolis.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.entity.Course;
import ru.innopolis.repository.CourseRepository;
import ru.innopolis.service.CourseService;

import java.util.*;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
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
        Course courseFromDb = courseRepository.save(courseInDb);

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
            Course courseFromDb = courseRepository.findById(id).orElseThrow();

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
        List<Course> courseFromDb = courseRepository.findAll();
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
            Course getCourse = courseRepository.findById(id).orElseThrow();

            if (getCourse.getId() != null) {
                Course courseNew = new Course(
                        id,
                        courseRequest.getName(),
                        courseRequest.getDate(),
                        courseRequest.getActive()
                );

                Course courseFromDb = courseRepository.save(courseNew);

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
        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public int deleteAllCourses() {
        courseRepository.deleteAll();
        return 1;
    }


    @Override
    public List<CourseResponse> findCoursesByIds(Set<Long> ids) {
        List<Course> courseFromDb = courseRepository.findAllById(ids);
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

}
