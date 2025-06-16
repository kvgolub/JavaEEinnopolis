package ru.innopolis.dto.studentCourse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.innopolis.entity.Student;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManyStudentsOneCourseResponse {

    private Long id;
    private String name;
    private Date date;
    private Boolean active;
    private List<Student> students;
}
