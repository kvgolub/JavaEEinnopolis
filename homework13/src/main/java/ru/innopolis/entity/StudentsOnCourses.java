package ru.innopolis.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentsOnCourses {

    @NotNull(message = "Идентификатор не может быть NULL")
    private Long id;

    @NotNull(message = "ID студента не может быть NULL")
    private Long studentId;

    @NotNull(message = "ID курса не может быть NULL")
    private Long courseId;

}
