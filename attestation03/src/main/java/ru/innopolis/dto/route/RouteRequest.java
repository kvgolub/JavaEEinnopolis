package ru.innopolis.dto.route;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RouteRequest {

    private String number;
    private Long startStation;
    private Long endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

}
