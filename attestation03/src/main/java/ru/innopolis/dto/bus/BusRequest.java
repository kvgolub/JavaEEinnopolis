package ru.innopolis.dto.bus;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusRequest {

    private Long type;
    private String model;
    private String regNumber;
    private Integer passengerCapacity;
    private Double price;

}
