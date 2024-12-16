package ru.innopolis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Aircraft {
    private String aircraft_code;
    private Object model;
    private Integer range;
}
