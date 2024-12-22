--Выборка всех маршрутов с детализацией
SELECT
	s.routeDate,
	bs.nameStop,
	bs2.nameStop,
	r.passengerFlow,
	b.model,
	bt.nameType,
	b.passengerCapacity,
	concat(d."name", ' ', d.surname)
FROM schedule s
INNER JOIN route r ON r.id = s.route_id
INNER JOIN busStop bs ON bs.id = r.startStation
INNER JOIN busStop bs2 ON bs2.id = r.endStation
INNER JOIN bus b ON b.id = s.bus_id
INNER JOIN busType bt ON bt.id = b."type"
INNER JOIN driver d ON d.id = s.driver_id;


--Выборка "На каких автобусах работает водитель Петров"
SELECT
	d.surname,
	b.model,
	b.regNumber
FROM driver d
INNER JOIN schedule s ON s.driver_id = d.id
INNER JOIN bus b ON b.id = s.bus_id
WHERE d.surname = 'Петров'


--Выборка "Какие маршруты начинаются/заканчиваются на Ж/д вокзале"
SELECT
	bs.nameStop,
	r."number"
FROM route r
INNER JOIN busStop bs ON bs.id = r.startStation OR bs.id = r.endStation
WHERE bs.nameStop = 'Ж/д вокзал';


--Выборка "У каких автобусов вместимость от 100 пассажиров и больше"
SELECT
	*
FROM bus
WHERE passengerCapacity >= 100;


--Выборка "Какой автобус самый дорогой"
SELECT
	*
FROM bus
WHERE price = (SELECT max(price) FROM bus);


--Выборка "На каком маршруте меньше всего остановок"
SELECT
	number,
	quantityStation
FROM route
ORDER BY
	quantityStation ASC LIMIT 1;


--Выборка "На каком маршруте с самым большм пассажиропотоком будет эффективен самый вместительный автобус и сколько ему понадобится рейсов"
WITH
	routeMaxPassengerFlow AS (
		SELECT
			*
		FROM route
		WHERE passengerFlow = (SELECT max(passengerFlow) FROM route)
	),
	busMaxPassengerCapacity AS (
		SELECT
			*
		FROM bus
		WHERE passengerCapacity = (
			SELECT
				max(passengerCapacity)
			FROM bus b
			INNER JOIN busType bt ON bt.id = b."type"
			WHERE bt.nameType != 'несуществующий'
		)
	)
SELECT
	(SELECT "number" FROM routeMaxPassengerFlow),
	(SELECT passengerFlow FROM routeMaxPassengerFlow),
	(SELECT model FROM busMaxPassengerCapacity),
	(SELECT passengerCapacity FROM busMaxPassengerCapacity),
	ceiling((SELECT passengerFlow FROM routeMaxPassengerFlow) / (SELECT passengerCapacity FROM busMaxPassengerCapacity)::numeric(15,2)) AS countRoutes;


--Замена автобуса в маршруте от "19.12.2024 19:10"
UPDATE schedule
SET
	bus_id = (SELECT id FROM bus WHERE model = 'ПАЗ'),
	driver_id = (SELECT id FROM driver WHERE surname = 'Пупкин')
WHERE routeDate = '2024-12-19T19:10:00';


--Замена Типа автобуса для марки "Камаз"
WITH cityBus AS (
	SELECT id FROM busType WHERE nameType = 'городской'
)
UPDATE bus
SET "type" = (SELECT * FROM cityBus)
WHERE model = 'Камаз';


--Удаление автобуса "FANTASTIC" из Таблицы Расписание
DELETE FROM schedule
WHERE bus_id = (SELECT id FROM bus WHERE model = 'FANTASTIC');


--Изменение Типа автобуса для записей "с гармошкой" на "низкопольный" в таблице Атобусы и удаление Типа автобуса "с гармошкой" из Таблицы Типа автобуса
WITH updateTypeBus AS (
	UPDATE bus
	SET "type" = (SELECT id FROM busType WHERE nameType = 'низкопольный')
	WHERE "type" = (SELECT id FROM busType WHERE nameType = 'с гармошкой')
)
DELETE FROM busType
WHERE nameType = 'с гармошкой';


--Показать общее количество начальных и конечнх остановок в таблице
SELECT
    count(*)
FROM busStop;
