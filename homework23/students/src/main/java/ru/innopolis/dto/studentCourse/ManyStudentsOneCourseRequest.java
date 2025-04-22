package ru.innopolis.dto.studentCourse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManyStudentsOneCourseRequest {

    @NotNull(message = "Список ID студентов не может быть NULL")
    @NotEmpty(message = "Список ID студентов не может быть пустым значением")
    private List<Long> studentId;

    @NotNull(message = "ID курса не может быть NULL")
    @Positive(message = "ID курса должно быть положительным числом")
    private Long coursesId;

}
