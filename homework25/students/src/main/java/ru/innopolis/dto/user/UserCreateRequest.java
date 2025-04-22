package ru.innopolis.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.innopolis.dto.student.StudentRequest;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {

    @NotNull(message = "Логин пользователя не может быть NULL")
    @NotBlank(message = "Логин пользователя не может быть пустым значением")
    private String username;

    @NotNull(message = "Пароль не может быть NULL")
    @NotBlank(message = "Пароль не может быть пустым значением")
    private String password;

    @NotNull(message = "Ролевые полномочия не могут быть NULL")
    @NotBlank(message = "Ролевые полномочия не могут быть пустым значением")
    @Pattern(message = "Некорректный формат ролевых полномочий", regexp = "^[A-Z]+$")
    private String authority;

    @NotNull(message = "Учетные данные студента не могут быть NULL")
//    @NotEmpty(message = "Учетные данные студента не могут быть пустым значением")
    private StudentRequest studentRequest;

}
