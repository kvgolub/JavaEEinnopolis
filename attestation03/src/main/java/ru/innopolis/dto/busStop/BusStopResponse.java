package ru.innopolis.dto.busStop;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusStopResponse {

    private Long id;
    private String nameStop;

}
