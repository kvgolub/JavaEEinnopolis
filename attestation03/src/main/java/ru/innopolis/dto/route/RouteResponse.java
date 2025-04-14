package ru.innopolis.dto.route;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouteResponse {

    private Long id;
    private String number;
    private Long startStation;
    private Long endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

}
