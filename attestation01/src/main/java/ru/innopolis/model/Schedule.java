package ru.innopolis.model;

import lombok.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Schedule {
    private Integer id;
    private Date routeDate;
    private Integer routeId;
    private Integer busId;
    private Integer driverId;
}
