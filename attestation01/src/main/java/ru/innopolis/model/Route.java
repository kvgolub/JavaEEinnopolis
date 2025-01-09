package ru.innopolis.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Route {
    private Integer id;
    private String number;
    private Integer startStation;
    private Integer endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

    public Route(String number, Integer quantityStation) {
        this.number = number;
        this.quantityStation = quantityStation;
    }

    public String getLeastQuantityStation() {
        return "Route(number=" + number + ", quantityStation=" + quantityStation + ")";
    }
}

