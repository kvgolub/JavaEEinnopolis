package ru.innopolis.dto.studentCourse;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OneStudentManyCoursesRequest {

    @NotNull(message = "ID студента не может быть NULL")
    @Positive(message = "ID студента должно быть положительным числом")
    private Long studentId;

    @NotNull(message = "Список ID курсов не может быть NULL")
    @NotEmpty(message = "Список ID курсов не может быть пустым значением")
    private List<Long> coursesId;

}
