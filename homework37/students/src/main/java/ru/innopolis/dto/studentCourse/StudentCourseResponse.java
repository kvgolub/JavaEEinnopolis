package ru.innopolis.dto.studentCourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourseResponse {

    private Long id;
    private Long studentId;
    private Long courseId;
}
