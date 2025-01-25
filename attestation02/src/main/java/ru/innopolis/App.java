package ru.innopolis;

import org.springframework.jdbc.BadSqlGrammarException;
import ru.innopolis.exception.NoIdObjectException;
import ru.innopolis.exception.SetIncorrectDataType;
import ru.innopolis.repository.*;
import ru.innopolis.repository.impl.*;

import java.util.Date;


public class App {
    private static final BusRepository BUS_REPOSITORY = new BusRepositoryImpl();
    private static final DriverRepository DRIVER_REPOSITORY = new DriverRepositoryImpl();
    private static final RouteRepository ROUTE_REPOSITORY = new RouteRepositoryImpl();
    private static final ScheduleRepository SCHEDULE_REPOSITORY = new ScheduleRepositoryImpl();
    private static final BusTypeRepository BUS_TYPE_REPOSITORY = new BusTypeRepositoryImpl();
    private static final BusStopRepository BUS_STOP_REPOSITORY = new BusStopRepositoryImpl();

    public static void main(String[] args) throws NoIdObjectException, SetIncorrectDataType {
        //Запросы для таблицы Автобусы
        System.out.println("=====Запросы для таблицы Автобусы:======================================================");

        System.out.println("-----Создание новой записи Автобуса \"VolgaBus\":-----------------------------------------");
        BUS_REPOSITORY.create(2, "VolgaBus", "т065рв", 50, 5000005.55);
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись об Автобусе по Идентификатору id=4:-----------------------------------");
        try {
            System.out.println(BUS_REPOSITORY.findById(4).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи об Автобусах:-----------------------------------------------------");
        var allBus = BUS_REPOSITORY.findAll();
        allBus.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи об Автобусе по Идентификатору id=4:-------------------------------");
        try {
            Integer resultUpdateBusAll = BUS_REPOSITORY.update(4,1, "ПАЗ-2", "Е404ок", 50, 1000000.00);
            if (resultUpdateBusAll == 0) {
                BUS_REPOSITORY.createWithId(4, 1, "ПАЗ-2", "Е404ок", 50, 1000000.00);
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Автобусы
        System.out.println("-----Найти \"У каких автобусов вместимость от 100 пассажиров и больше\":------------------");
        var largePassengerCapacity = BUS_REPOSITORY.findLargePassengerCapacity(100);
        largePassengerCapacity.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти \"Какой автобус самый дорогой\":-----------------------------------------------");
        var mostExpensive = BUS_REPOSITORY.findMostExpensive();
        mostExpensive.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Водители
        System.out.println("\n=====Запросы для таблицы Водители:======================================================");

        System.out.println("-----Создание новой записи Водители \"Василий Васичкин\":---------------------------------");
        DRIVER_REPOSITORY.create("Васичкин", "Василий", 39);
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись о Водителе по Идентификатору id=2:------------------------------------");
        try {
            System.out.println(DRIVER_REPOSITORY.findById(2).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи о Водителях:------------------------------------------------------");
        var allDriver = DRIVER_REPOSITORY.findAll();
        allDriver.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи о Водителе по Идентификатору id=6:--------------------------------");
        try {
            Integer resultUpdateDriverAll = DRIVER_REPOSITORY.update(6,"Тимофеев", "Тимофей", 45);
            if (resultUpdateDriverAll == 0) {
                DRIVER_REPOSITORY.createWithId(6, "Тимофеев", "Тимофей", 45);
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Водители
        System.out.println("-----Выборка \"На каких автобусах работает водитель Петров\":-----------------------------");
        var driverOnBus = DRIVER_REPOSITORY.findDriverOnBus("Петров");
        driverOnBus.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Маршруты
        System.out.println("\n=====Запросы для таблицы Маршруты:======================================================");

        System.out.println("-----Создание новой записи Маршруты \"300А\":---------------------------------------------");
        ROUTE_REPOSITORY.create("300А", 4, 8, 14, 650);
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись о Маршруте по Идентификатору id=8:------------------------------------");
        try {
            System.out.println(ROUTE_REPOSITORY.findById(8).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи о Маршрутах:------------------------------------------------------");
        var allRoute = ROUTE_REPOSITORY.findAll();
        allRoute.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи о Маршруте по Идентификатору id=6:--------------------------------");
        try {
            Integer resultUpdateRouteAll = ROUTE_REPOSITORY.update(10,"200C", 8, 2, 20, 750);
            if (resultUpdateRouteAll == 0) {
                ROUTE_REPOSITORY.createWithId(10,"200C", 8, 2, 20, 750);
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Маршруты("Ж/д вокзал");
        System.out.println("-----Выборка \"Какие маршруты начинаются/заканчиваются на Ж/д вокзале\":------------------");
        var containBusStop = ROUTE_REPOSITORY.findContainBusStop("Ж/д вокзал");
        containBusStop.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Выборка \"На каком маршруте меньше всего остановок\":--------------------------------");
        var leastQuantityStation = ROUTE_REPOSITORY.findLeastQuantityStation();
        leastQuantityStation.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Выборка \"На каком маршруте с самым большим пассажиропотоком будет эффективен самый\nвместительный автобус и сколько ему понадобится рейсов\":----------------");
        var largePassengerFlowAndEffectBus = ROUTE_REPOSITORY.findLargePassengerFlowAndEffectBus("несуществующий");
        largePassengerFlowAndEffectBus.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Расписание
        System.out.println("\n=====Запросы для таблицы Расписание:====================================================");

        System.out.println("-----Создание новой записи Расписание \"20.12.2024 10:20\":-------------------------------");
        SCHEDULE_REPOSITORY.create("2024-12-20T10:20:00", 5, 7, 3);
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись о Расписании по Идентификатору id=7:----------------------------------");
        try {
            System.out.println(SCHEDULE_REPOSITORY.findById(7).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи о Расписаниях:----------------------------------------------------");
        var allSchedule = SCHEDULE_REPOSITORY.findAll();
        allSchedule.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи о Расписании по Идентификатору id=3:------------------------------");
        try {
            Integer resultUpdateScheduleAll = SCHEDULE_REPOSITORY.update(3,"2024-12-15T17:00:00", 5, 1, 6);
            if (resultUpdateScheduleAll == 0) {
                SCHEDULE_REPOSITORY.createWithId(3,"2024-12-15T17:00:00", 5, 1, 6);
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Расписание;
        System.out.println("-----Выборка \"На каком маршруте меньше всего остановок\":--------------------------------");
        var allScheduleDetail = SCHEDULE_REPOSITORY.findAllScheduleDetail();
        allScheduleDetail.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Автобусные остановки
        System.out.println("\n=====Запросы для таблицы Автобусные остановки:==========================================");

        System.out.println("-----Создание новой записи Автобусные остановки \"Путь Ильича\":--------------------------");
        BUS_STOP_REPOSITORY.create("Путь Ильича");
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись об Автобусной остановке по Идентификатору id=5:-----------------------");
        try {
            System.out.println(BUS_STOP_REPOSITORY.findById(5).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи об Автобусных остановках:-----------------------------------------");
        var allBusStop = BUS_STOP_REPOSITORY.findAll();
        allBusStop.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи об Автобусной остановке по Идентификатору id=9:-------------------");
        try {
            Integer resultUpdateBusStopAll = BUS_STOP_REPOSITORY.update(9,"Деревня Верхние Низинки");
            if (resultUpdateBusStopAll == 0) {
                BUS_STOP_REPOSITORY.createWithId(9,"Деревня Верхние Низинки");
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Автобусные остановки;
        System.out.println("-----Показать общее количество начальных и конечных остановок в таблице:----------------");
        var findCountBusStop = BUS_STOP_REPOSITORY.findQuantityBusStop();
        System.out.println(findCountBusStop.get(0));
        System.out.println("----------------------------------------------------------------------------------------\n");


        //Запросы для таблицы Типы автобуса
        System.out.println("\n=====Запросы для таблицы Типы автобуса:=================================================");

        System.out.println("-----Создание новой записи Типы автобуса \"Скоростной\":----------------------------------");
        BUS_TYPE_REPOSITORY.create("скоростной");
        System.out.println("Выполнено\n");

        System.out.println("-----Найти запись о Типе автобуса по Идентификатору id=4:-------------------------------");
        try {
            System.out.println(BUS_TYPE_REPOSITORY.findById(4).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
        }
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Найти все записи о Типах автобусов:------------------------------------------------");
        var allBusType = BUS_TYPE_REPOSITORY.findAll();
        allBusType.stream()
                .toList()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------\n");

        System.out.println("-----Изменение записи о Типе автобуса по Идентификатору id=6:---------------------------");
        try {
            Integer resultUpdateBusStopAll = BUS_TYPE_REPOSITORY.update(6,"автобус-снегоболотоход");
            if (resultUpdateBusStopAll == 0) {
                BUS_TYPE_REPOSITORY.createWithId(6,"автобус-снегоболотоход");
                throw new NoIdObjectException("<Object> с заданным идентификатором не существует. Создана новая запись с указанным идентификатором");
            }
        } catch (BadSqlGrammarException e) {
            throw new SetIncorrectDataType("Нарушено требование к полям. Необходимо проверить передаваемые типы данных в параметрах");
        }
        System.out.println("Выполнено\n");

        //Дополнительные запросы на выборку в таблице Автобусные остановки;
        BUS_TYPE_REPOSITORY.updateAndDeleteBusType("низкопольный", "с гармошкой", "с гармошкой");
        System.out.println("-----Изменение Типа автобуса для записей \"с гармошкой\" на \"низкопольный\" в таблице\nАвтобусы и удаление Типа автобуса \"с гармошкой\" из Таблицы Типа автобуса:---------------");
        System.out.println("Выполнено\n");



        //Выключатель удалений записей из таблиц по идентификатору
        int switchDeleteById = 0;

        //Удаление выборочных записей
        System.out.println("\n=====Удаление выборочных записей таблиц по Идентификатору:==============================");
        switch (switchDeleteById) {
            case 0:
                System.out.println("-----Удаление записи об Автобусе по Идентификатору id=10: Отключено");
                System.out.println("-----Удаление записи о Водителе по Идентификатору id=5: Отключено");
                System.out.println("-----Удаление записи о Маршруте по Идентификатору id=4: Отключено");
                System.out.println("-----Удаление записи о Расписании по Идентификатору id=8: Отключено");
                System.out.println("-----Удаление записи об Автобусной остановке по Идентификатору id=2: Отключено");
                System.out.println("-----Удаление записи о Типе автобуса по Идентификатору id=7: Отключено");

                break;
            case 1:
                Integer busResultDeleteById = BUS_REPOSITORY.deleteByID(10);
                if (busResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи об Автобусе по Идентификатору id=10: Выполнено");

                Integer driverResultDeleteById = DRIVER_REPOSITORY.deleteByID(5);
                if (driverResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи о Водителе по Идентификатору id=5: Выполнено");

                Integer routeResultDeleteById = ROUTE_REPOSITORY.deleteByID(4);
                if (routeResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи о Маршруте по Идентификатору id=4: Выполнено");

                Integer scheduleResultDeleteById = SCHEDULE_REPOSITORY.deleteByID(8);
                if (scheduleResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи о Расписании по Идентификатору id=8: Выполнено");

                Integer busStopResultDeleteById = BUS_STOP_REPOSITORY.deleteByID(2);
                if (busStopResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи об Автобусной остановке по Идентификатору id=2: Выполнено");

                Integer busTypeResultDeleteById = BUS_TYPE_REPOSITORY.deleteByID(7);
                if (busTypeResultDeleteById == 0) {
                    throw new NoIdObjectException("<Object> с заданным идентификатором не существует");
                }
                System.out.println("-----Удаление записи о Типе автобуса по Идентификатору id=7: Выполнено");

                break;
        }



        //Выключатель удалений всех записей из таблиц
        int switchDeleteAll = 0;

        //Удаление всех записей таблиц
        System.out.println("\n=====Удаление всех записей таблиц:======================================================");
        switch (switchDeleteAll) {
            case 0:
                System.out.println("-----Удаление всех записей об Автобусах: Отключено");
                System.out.println("-----Удаление всех записей о Водителях: Отключено");
                System.out.println("-----Удаление всех записей о Маршрутах: Отключено");
                System.out.println("-----Удаление всех записей о Расписаниях: Отключено");
                System.out.println("-----Удаление всех записей об Автобусных остановках: Отключено");
                System.out.println("-----Удаление всех записей о Типах автобусов: Отключено");

                break;
            case 1:
                BUS_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей об Автобусах: Выполнено");

                DRIVER_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей о Водителях: Выполнено");

                ROUTE_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей о Маршрутах: Выполнено");

                SCHEDULE_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей о Расписаниях: Выполнено");

                BUS_STOP_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей об Автобусных остановках: Выполнено");

                BUS_TYPE_REPOSITORY.deleteAll();
                System.out.println("-----Удаление всех записей о Типах автобусов: Выполнено");

                break;
        }
    }
}
