package ru.innopolis.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private String email;
}
