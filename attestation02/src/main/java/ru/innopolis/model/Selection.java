package ru.innopolis.model;

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
    private Integer idBus;
    private Integer type;
    private String model;
    private String regNumber;
    private Integer passengerCapacity;
    private Float price;

    //BusStop
    private Integer idBusStop;
    private String nameStop;

    //BusType
    private Integer idBusType;
    private String nameType;

    //Driver
    private Integer idDriver;
    private String surname;
    private String name;
    private Integer age;

    //Route
    private Integer idRoute;
    private String number;
    private Integer startStation;
    private Integer endStation;
    private Integer quantityStation;
    private Integer passengerFlow;

    //Schedule
    private Integer idSchedule;
    private Date routeDate;
    private Integer routeId;
    private Integer busId;
    private Integer driverId;

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
