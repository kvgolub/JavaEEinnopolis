package ru.innopolis.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String name;
    private Date date;
    private Boolean active;
}
