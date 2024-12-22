--Выборка всех записей в таблице Самолеты
SELECT * FROM aircrafts_data;


--Вставка новой записи в таблицу Самолеты
INSERT INTO aircrafts_data(aircraft_code, model, range) VALUES ('M21', '{"en": "MS-21", "ru": "МС-21"}'::jsonb, 2100);


--Обновление запсиси в таблице Самолеты
UPDATE aircrafts_data SET range = 2000 WHERE aircraft_code = 'CN1';


--Удаление записи из таблицы Самолеты
DELETE FROM aircrafts_data WHERE aircraft_code = 'M22'; --M22 - созданая вручную тестовая запись


--Поиск записи о конкретной модели самолета в таблице Самолеты
SELECT * FROM aircrafts_data WHERE (model ->> 'en') = 'Boeing 737-300';


--Выборка всех записей в таблице Аэропорты
SELECT * FROM airports_data;


--Вставка новой записи в таблицу Аэропорты
INSERT INTO airports_data(airport_code, airport_name, city, coordinates, timezone)
VALUES ('KG2', '{"en": "Khrabrovo Airpor 2", "ru": "Храброво-2"}'::jsonb, '{"en": "Kaliningrad", "ru": "Калининград"}'::jsonb, '(20.592599868774414,54.88999938964844)'::point, 'Europe/Kaliningrad');


--Обновление запсиси в таблице Аэропорты
UPDATE airports_data SET timezone = 'Europe/Moscow' WHERE airport_code = 'KUF';


--Удаление записи из таблицы Аэропорты
DELETE FROM airports_data WHERE airport_code = 'KG3'; --KG3 - созданая вручную тестовая запись


--
SELECT * FROM airports_data WHERE (airport_name ->> 'en') = 'Kazan International Airport';


--Выборка состоявшихся полетов для определенного Самолета
SELECT
    f.*
FROM flights f
INNER JOIN aircrafts_data ad ON ad.aircraft_code = f.aircraft_code
WHERE (ad.model ->> 'en') = 'Boeing 737-300'
	AND f.status = 'Arrived'
