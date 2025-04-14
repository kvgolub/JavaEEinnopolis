package ru.innopolis.dto.schedule;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleResponse {

    private Long id;
    private Date routeDate;
    private Long routeId;
    private Long busId;
    private Long driverId;

}
