package ru.innopolis.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Driver {
    private Integer id;
    private String surname;
    private String name;
    private Integer age;
}

