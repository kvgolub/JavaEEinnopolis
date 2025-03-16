package ru.innopolis.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponse {

    private Long id;
    private Long studentId;
    private Long coursesId;
    private String text;

}
