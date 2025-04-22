package ru.innopolis.dto.studentCourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OneStudentManyCoursesResponse {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private String email;
    private List<CourseResponse> courses;

}
