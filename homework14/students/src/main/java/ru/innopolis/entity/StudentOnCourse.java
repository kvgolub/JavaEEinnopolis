package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentOnCourse {

    private Long id;
    private Long studentId;
    private Long courseId;

}
