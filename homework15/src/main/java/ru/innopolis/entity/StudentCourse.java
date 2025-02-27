package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCourse {

    private Long id;
    private Long studentId;
    private Long courseId;

}
