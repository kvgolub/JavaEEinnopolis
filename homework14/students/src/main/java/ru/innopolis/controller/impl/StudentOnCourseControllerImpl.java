package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.StudentOnCourseController;
import ru.innopolis.dto.student.StudentOnCourseRequest;
import ru.innopolis.dto.student.StudentOnCourseResponse;
import ru.innopolis.service.StudentOnCourseService;


@RestController
@RequiredArgsConstructor
public class StudentOnCourseControllerImpl implements StudentOnCourseController {

    private final StudentOnCourseService studentInManyCoursesService;

    @Override
    public ResponseEntity<StudentOnCourseResponse> createStudentInManyCourses(StudentOnCourseRequest studentOnCourseRequest) {
        StudentOnCourseResponse studentOnCourseResponse = studentInManyCoursesService.createStudentInManyCourses(studentOnCourseRequest);

        if (studentOnCourseResponse != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentOnCourseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
