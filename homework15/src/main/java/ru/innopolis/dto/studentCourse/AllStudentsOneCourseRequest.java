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
public class AllStudentsOneCourseRequest {

    @NotNull(message = "ID курса не может быть NULL")
    @Positive(message = "ID курса должно быть положительным числом")
    private Long coursesId;

}
