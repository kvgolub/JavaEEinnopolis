package ru.innopolis.dto.driver;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DriverResponse {

    private Long id;
    private String surname;
    private String name;
    private Integer age;

}
