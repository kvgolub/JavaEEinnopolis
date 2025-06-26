package ru.innopolis.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.innopolis.dto.student.StudentResponse;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserStudentResponse {

    private StudentResponse studentResponse;
}
