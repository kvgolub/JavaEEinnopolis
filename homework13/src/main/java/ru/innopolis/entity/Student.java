package ru.innopolis.entity;

import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    private Long id;

    @NotNull(message = "Фамилия не может быть NULL")
    @NotBlank(message = "Фамилия не может быть пустым значением")
    @Pattern(message = "Некорректный формат фамилии", regexp = "^[А-Я][а-я]+$")
    private String surname;

    @NotNull(message = "Имя не может быть NULL")
    @NotBlank(message = "Имя не может быть пустым значением")
    @Pattern(message = "Некорректный формат имени", regexp = "^[А-Я][а-я]+$")
    private String name;

    @NotNull(message = "Отчество не может быть NULL")
    @NotBlank(message = "Отчество не может быть пустым значением")
    @Pattern(message = "Некорректный формат отчества",
            regexp = "^[А-Я][а-я]+$")
    private String patronymic;

    @NotNull(message = "Электронная почта не может быть NULL")
    @NotBlank(message = "Электронная почта не может быть пустым значением")
    @Email(message = "Некорректный формат электронной почты: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    public Student(String surname, String name, String patronymic, String email) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
    }

}
