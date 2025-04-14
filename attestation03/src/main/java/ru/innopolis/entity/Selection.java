package ru.innopolis.entity;

import lombok.*;

import java.util.Date;


/**
 * Класс для соединительной таблицы многие-ко-многим
 */
@ToString
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Selection {

    //Bus
    private Long idBus;
    private Long type;
    private String model;
    private String regNumber;
    private Integer passengerCapacity;
    private Float price;

    //BusStop
    private Long idBusStop;
    private String nameStop;

    //BusType
    private Long idBusType;
    private String nameType;

    //Driver
    private Long idDriver;
    private String surname;
    private String name;
    private Integer age;

    //Route
    private Long idRoute;
    private String number;
    private Long startStation;
    private Long endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

    //Schedule
    private Long idSchedule;
    private Date routeDate;
    private Long routeId;
    private Long busId;
    private Long driverId;

    public String getSelectionDriverBus(String surname, String model, String regNumber) {
        return "Driver(surname=" + surname  + ", model=" + model + ", regNumber=" + regNumber + ")";
    }

    public String getContainBusStop(String nameStop, String number) {
        return "Route(nameStop=" + nameStop  + ", number=" + number + ")";
    }

    public String getLargePassengerFlowAndEffectBus(String number, Integer passengerFlow, String model, Integer passengerCapacity, Float countRoutes) {
        return "Route(number=" + number + ", passengerFlow=" + passengerFlow + ", model=" + model + ", passengerCapacity=" + passengerCapacity + ", countRoutes=" + countRoutes + ")";
    }

    public String getAllScheduleDetail(Date routeDate, String nameStopStart, String nameStopEnd, Integer passengerFlow, String model, String nameType, Integer passengerCapacity, String driverNs) {
        return "Schedule(routeDate=" + routeDate + ", nameStopStart=" + nameStopStart + ", nameStopEnd=" + nameStopEnd + ", passengerFlow=" + passengerFlow + ", model=" + model + ", nameType=" + nameType + ", passengerCapacity=" + passengerCapacity + ", driverNs=" + driverNs + ")";
    }

}
