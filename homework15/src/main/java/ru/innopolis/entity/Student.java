package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String email;

}
