package ru.innopolis.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestParam {

    @NotNull(message = "ID студента не может быть NULL")
    Long studentId;

    @NotNull(message = "Список ID курсов не может быть NULL")
    @NotEmpty(message = "Список ID курсов не может быть пустым значением")
    List<Long> courses;

}
