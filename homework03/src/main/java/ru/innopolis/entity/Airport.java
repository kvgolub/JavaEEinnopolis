package ru.innopolis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Array;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Airport {
    private String airport_code;
    private Object airport_name;
    private Object city;
    private String coordinates;
    private String timezone;
}
