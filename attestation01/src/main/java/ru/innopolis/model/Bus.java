package ru.innopolis.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bus {
    private Integer id;
    private Integer type;
    private String model;
    private String regNumber;
    private Integer passengerCapacity;
    private Float price;
}
