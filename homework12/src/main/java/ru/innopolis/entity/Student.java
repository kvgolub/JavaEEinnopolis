package ru.innopolis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Фамилия не может быть NULL")
    @NotBlank(message = "Фамилия не может быть пустым значением")
    @Pattern(message = "Некорректный формат фамилии", regexp = "^[А-Я][а-я]+$")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Имя не может быть NULL")
    @NotBlank(message = "Имя не может быть пустым значением")
    @Pattern(message = "Некорректный формат имени", regexp = "^[А-Я][а-я]+$")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Отчество не может быть NULL")
    @NotBlank(message = "Отчество не может быть пустым значением")
    @Pattern(message = "Некорректный формат отчества",
            regexp = "^[А-Я][а-я]+$")
    @Column(name = "patronymic")
    private String patronymic;

    @NotNull(message = "Электронная почта не может быть NULL")
    @NotBlank(message = "Электронная почта не может быть пустым значением")
    @Email(message = "Некорректный формат электронной почты: ${validatedValue}",
            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Список курсов не может быть NULL")
    @NotEmpty(message = "Список курсов не может быть пустым значением")
    @Column(name = "course")
    private Set<String> course;

}
