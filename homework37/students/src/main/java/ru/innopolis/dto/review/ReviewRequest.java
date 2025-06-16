package ru.innopolis.dto.review;

import jakarta.validation.constraints.NotBlank;
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
public class ReviewRequest {

    @NotNull(message = "ID студента не может быть NULL")
    @Positive(message = "ID студента должно быть положительным числом")
    private Long studentId;

    @NotNull(message = "ID курса не может быть NULL")
    @Positive(message = "ID курса должно быть положительным числом")
    private Long coursesId;

    @NotNull(message = "Отзыв не может быть NULL")
    @NotBlank(message = "Отзыв не может быть пустым значением")
    private String text;
}
