package ru.innopolis.dto.course;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponse {

    private Long id;
    private String name;
    private Date date;
    private Boolean active;

}
