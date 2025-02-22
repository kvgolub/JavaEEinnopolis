package ru.innopolis.dto.studentCourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.innopolis.dto.student.StudentResponse;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllStudentsOneCourseResponse {

    private Long id;
    private String name;
    private String date;
    private Boolean active;
    private List<StudentResponse> students;

}
