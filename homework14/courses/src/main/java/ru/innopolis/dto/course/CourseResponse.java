package ru.innopolis.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String name;
    private String date;
    private Boolean active;

}
