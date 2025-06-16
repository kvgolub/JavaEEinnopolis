package ru.innopolis.dto.mail;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailRequest {

    private List<String> email;
    private String message;
}
