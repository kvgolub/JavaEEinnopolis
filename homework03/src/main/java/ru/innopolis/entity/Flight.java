package ru.innopolis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Flight {
    private Integer flight_id;
    private String flight_no;
    private Date scheduled_departure;
    private Date scheduled_arrival;
    private String departure_airport;
    private String arrival_airport;
    private String status;
    private String aircraft_code;
    private Date actual_departure;
    private Date actual_arrival;
}
