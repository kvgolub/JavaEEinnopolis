package ru.innopolis.dto.studentCourse;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourseRequest {

    @NotNull(message = "ID студента не может быть NULL")
    @Positive(message = "ID студента должно быть положительным числом")
    private Long studentId;

    @NotNull(message = "ID курса не может быть NULL")
    @Positive(message = "ID курса должно быть положительным числом")
    private Long coursesId;

}
