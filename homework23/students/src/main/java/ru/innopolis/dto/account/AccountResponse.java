package ru.innopolis.dto.account;

import lombok.*;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private List<CourseResponse> courseResponseList;

}
