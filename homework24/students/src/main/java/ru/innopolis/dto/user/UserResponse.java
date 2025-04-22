package ru.innopolis.dto.user;

import lombok.*;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private List<CourseResponse> courseResponseList;

}
