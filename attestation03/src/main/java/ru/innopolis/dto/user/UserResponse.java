package ru.innopolis.dto.user;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private String username;
    private String password;
    private Boolean enabled;
    private List<String> authorities;

}
