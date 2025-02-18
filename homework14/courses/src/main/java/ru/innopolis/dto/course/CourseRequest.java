package ru.innopolis.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRequest {

    @NotNull(message = "Курс не может быть NULL")
    @NotBlank(message = "Курс не может быть пустым значением")
    private String name;

    @NotNull(message = "Дата не может быть NULL")
    @NotBlank(message = "Дата не может быть пустым значением")
    @Pattern(message = "Некорректный формат даты", regexp = "^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")
    private String date;

    @NotNull(message = "Признак активности не может быть NULL")
    private Boolean active;

}
