package ru.innopolis.dto.student;

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
public class StudentOnCourseResponse {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private List<CourseResponse> courses;

}
