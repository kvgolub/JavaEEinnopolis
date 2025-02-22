package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.CourseController;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.service.CourseService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    @Override
    public ResponseEntity<CourseResponse> createCourse(CourseRequest courseRequest) {
        CourseResponse courseResponse = courseService.createCourse(courseRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
    }

    @Override
    public ResponseEntity<Optional<CourseResponse>> findByIdCourse(Long courseId) {
        Optional<CourseResponse> courseResponse = courseService.findByIdCourse(courseId);

        return courseResponse.isPresent()
                ? ResponseEntity.ok().body(courseResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseResponse);
    }

    @Override
    public ResponseEntity<List<CourseResponse>> findAllCourses() {
        List<CourseResponse> courseResponses = courseService.findAllCourses();

        return !courseResponses.isEmpty()
                ? ResponseEntity.ok().body(courseResponses)
                : ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Optional<CourseResponse>> updateCourse(Long id, CourseRequest courseRequest) {
        Optional<CourseResponse> courseResponse = courseService.updateCourse(id, courseRequest);

        if (courseResponse.isPresent()) {
            return ResponseEntity.ok().body(courseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseResponse);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteByIdCourse(Long courseId) {
        boolean courseDelete = courseService.deleteByIdCourse(courseId);

        if (courseDelete) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @Override
    public ResponseEntity<Integer> deleteAllCourses() {
        int courseDeleteAll = courseService.deleteAllCourses();

        if (courseDeleteAll > 0) {
            return ResponseEntity.ok().body(courseDeleteAll);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
