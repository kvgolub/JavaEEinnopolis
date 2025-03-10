package ru.innopolis.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullDataReviewResponse {

    private Long studentId;
    private String surname;
    private String studentName;
    private String patronymic;
    private Integer age;
    private String email;

    private Long courseId;
    private String courseName;
    private Date date;
    private Boolean active;

    private String text;

}
