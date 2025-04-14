package ru.innopolis.dto.busType;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusTypeResponse {

    private Long id;
    private String nameType;

}
