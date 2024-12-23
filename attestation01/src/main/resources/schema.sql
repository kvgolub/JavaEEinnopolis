--Создание таблицы Автобусы
CREATE TABLE IF NOT EXISTS bus(
	id SERIAL PRIMARY KEY,
	"type" INTEGER,
	model VARCHAR(50),
	regNumber VARCHAR(6),
	passengerCapacity INTEGER,
	price NUMERIC
);


--Создание таблицы Маршруты
CREATE TABLE IF NOT EXISTS route(
	id SERIAL PRIMARY KEY,
	"number" varchar(5),
	startStation INTEGER,
	endStation INTEGER,
	quantityStation INTEGER,
	passengerFlow INTEGER
);


--Создание таблицы Водители
CREATE TABLE IF NOT EXISTS driver(
	id SERIAL PRIMARY KEY,
	surname VARCHAR(30),
	name VARCHAR(30),
	age INTEGER
);


--Создание таблицы Расписание (связь многие-ко-многим)
CREATE TABLE IF NOT EXISTS schedule(
	id SERIAL PRIMARY KEY,
	routeDate TIMESTAMP,
	route_id INTEGER,
	bus_id INTEGER,
	driver_id INTEGER
);


--Создание таблицы Автобусне остановки (связь один-ко-многим)
CREATE TABLE IF NOT EXISTS busStop(
	id SERIAL PRIMARY KEY,
	nameStop VARCHAR(50)
);


--Создание таблицы Тип автобуса (связь один-ко-многим)
CREATE TABLE IF NOT EXISTS busType(
	id SERIAL PRIMARY KEY,
	nameType VARCHAR(50)
);


--Добавление внешнего ключа для таблицы Автобусы (к таблице Тип автобуса)
ALTER TABLE bus
ADD CONSTRAINT fk_type FOREIGN KEY ("type") REFERENCES busType(id);


--Добавление внешних ключей для таблицы Маршруты (к таблице Автобусне остановки)
ALTER TABLE route
ADD CONSTRAINT fk_startStation FOREIGN KEY (startStation) REFERENCES busStop(id),
ADD CONSTRAINT fk_endStation FOREIGN KEY (endStation) REFERENCES busStop(id);


--Добавление внешних ключей для таблицы Расписание (к таблицам Маршруты, Автобусы, Водители)
ALTER TABLE schedule
ADD CONSTRAINT fk_route_id FOREIGN KEY (route_id) REFERENCES route(id),
ADD CONSTRAINT fk_bus_id FOREIGN KEY (bus_id) REFERENCES bus(id),
ADD CONSTRAINT fk_driver_id FOREIGN KEY (driver_id) REFERENCES driver(id);
