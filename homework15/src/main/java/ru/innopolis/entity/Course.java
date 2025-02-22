package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    private Long id;
    private String name;
    private String date;
    private Boolean active;

}
