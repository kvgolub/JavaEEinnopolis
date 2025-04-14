package ru.innopolis.dto.driver;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DriverRequest {

    private String surname;
    private String name;
    private Integer age;

}
