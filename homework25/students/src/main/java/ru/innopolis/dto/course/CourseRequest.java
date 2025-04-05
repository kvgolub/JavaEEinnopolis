package ru.innopolis.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRequest {

    @NotNull(message = "Курс не может быть NULL")
    @NotBlank(message = "Курс не может быть пустым значением")
    private String name;

    @NotNull(message = "Дата не может быть NULL")
    private Date date;

    @NotNull(message = "Признак активности не может быть NULL")
    private Boolean active;

}
