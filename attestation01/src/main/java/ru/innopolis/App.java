package ru.innopolis;

import ru.innopolis.repository.*;
import ru.innopolis.repository.impl.*;


public class App {
    private static final BusRepository BUS_REPOSITORY = new BusRepositoryImpl();
    private static final DriverRepository DRIVER_REPOSITORY = new DriverRepositoryImpl();
    private static final RouteRepository ROUTE_REPOSITORY = new RouteRepositoryImpl();
    private static final ScheduleRepository SCHEDULE_REPOSITORY = new ScheduleRepositoryImpl();
    private static final BusTypeRepository BUS_TYPE_REPOSITORY = new BusTypeRepositoryImpl();
    private static final BusStopRepository BUS_STOP_REPOSITORY = new BusStopRepositoryImpl();

    public static void main(String[] args) {
        //Запросы для таблицы Автобусы
        var largePassengerCapacity = BUS_REPOSITORY.findLargePassengerCapacity(100);
        System.out.println("-----Выборка \"У каких автобусов вместимость от 100 пассажиров и больше\":--------------");
        largePassengerCapacity.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");

        var mostExpensive = BUS_REPOSITORY.findMostExpensive();
        System.out.println("-----Выборка \"Какой автобус самый дорогой\":-------------------------------------------");
        mostExpensive.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");

        BUS_REPOSITORY.updateBusType("городской", "Камаз");
        System.out.println("-----Замена Типа автобуса для марки \"Камаз\":------------------------------------------");
        System.out.println("Выполнено\n");


        //Запросы для таблицы Водители
        var driverOnBus = DRIVER_REPOSITORY.findDriverOnBus("Петров");
        System.out.println("-----Выборка \"На каких автобусах работает водитель Петров\":---------------------------");
        driverOnBus.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Маршруты
        var containBusStop = ROUTE_REPOSITORY.findContainBusStop("Ж/д вокзал");
        System.out.println("-----Выборка \"Какие маршруты начинаются/заканчиваются на Ж/д вокзале\":----------------");
        containBusStop.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");

        var leastQuantityStation = ROUTE_REPOSITORY.findLeastQuantityStation();
        System.out.println("-----Выборка \"На каком маршруте меньше всего остановок\":------------------------------");
        leastQuantityStation.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");

        var largePassengerFlowAndEffectBus = ROUTE_REPOSITORY.findLargePassengerFlowAndEffectBus("несуществующий");
        System.out.println("-----Выборка \"На каком маршруте с самым большм пассажиропотоком будет эффективен самый\nвместительный автобус и сколько ему понадобится рейсов\":----------------");
        largePassengerFlowAndEffectBus.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Расписание
        var allScheduleDetail = SCHEDULE_REPOSITORY.findAllScheduleDetail();
        System.out.println("-----Выборка \"На каком маршруте меньше всего остановок\":------------------------------");
        allScheduleDetail.forEach(System.out::println);
        System.out.println("--------------------------------------------------------------------------------------\n");

        SCHEDULE_REPOSITORY.updateBusOnDate("ПАЗ", "Пупкин", "2024-12-19T19:10:00");
        System.out.println("-----Замена автобуса в маршруте от \"19.12.2024 19:10\":--------------------------------");
        System.out.println("Выполнено\n");

        SCHEDULE_REPOSITORY.deleteBusFromSchedule("FANTASTIC");
        System.out.println("-----Удаление автобуса \"FANTASTIC\" из Таблицы Расписание:-----------------------------");
        System.out.println("Выполнено\n");

        //Запросы для таблицы Расписание
        BUS_TYPE_REPOSITORY.updateAndDeleteBusType("низкопольный", "с гармошкой", "с гармошкой");
        System.out.println("-----Изменение Типа автобуса для записей \"с гармошкой\" на \"низкопольный\" в таблице\nАтобусы и удаление Типа автобуса \"с гармошкой\" из Таблицы Типа автобуса:--------------");
        System.out.println("Выполнено\n");


        //Запросы для таблицы Автобусне остановки
        var findCountBusStop = BUS_STOP_REPOSITORY.findQuantityBusStop();
        System.out.println("-----Показать общее количество начальных и конечнх остановок в таблице:---------------");
        System.out.println(findCountBusStop.get(0));
        System.out.println("--------------------------------------------------------------------------------------\n");
    }
}
