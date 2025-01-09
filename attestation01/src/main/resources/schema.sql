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


--Добавление комментария о БД
COMMENT ON DATABASE attestation01 IS 'база данных Промежуточная аттестация 1';


--Добавление комментария о Схеме
COMMENT ON SCHEMA bus_station IS 'схема Автобусный парк';


--Добавление комментариев по таблицам
COMMENT ON TABLE bus IS 'таблица Автобусы';
COMMENT ON TABLE route IS 'таблица Маршруты';
COMMENT ON TABLE driver IS 'таблица Водители';
COMMENT ON TABLE schedule IS 'таблица Расписание (связь многие-ко-многим)';
COMMENT ON TABLE busstop IS 'таблица Автобусне остановки (связь один-ко-многим)';
COMMENT ON TABLE bustype IS 'таблица Тип автобуса (связь один-ко-многим)';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Автобусы
COMMENT ON COLUMN bus.id IS 'ID записи автобуса';
COMMENT ON COLUMN bus."type" IS 'Тип автобуса';
COMMENT ON COLUMN bus.model IS 'Марка автобуса';
COMMENT ON COLUMN bus.regnumber IS 'Регистарционный номер автобуса';
COMMENT ON COLUMN bus.passengercapacity IS 'Пассажировместимость автобуса';
COMMENT ON COLUMN bus.price IS 'Стоимость автобуса';


--Колонки таблицы Маршруты
COMMENT ON COLUMN route.id IS 'ID записи маршрута';
COMMENT ON COLUMN route."number" IS 'Номер маршрута';
COMMENT ON COLUMN route.startStation IS 'Остановка отправдения на маршруте';
COMMENT ON COLUMN route.endStation IS 'Остановка прибытия на маршруте';
COMMENT ON COLUMN route.quantitystation IS 'Количество остановок на маршруте';
COMMENT ON COLUMN route.passengerflow IS 'Пассажиропоток на маршруте';


--Колонки таблицы Водители
COMMENT ON COLUMN driver.id IS 'ID записи водителя';
COMMENT ON COLUMN driver.surname IS 'Фамилия водителя';
COMMENT ON COLUMN driver."name" IS 'Имя водителя';
COMMENT ON COLUMN driver.age IS 'Возраст водителя';


--Колонки таблицы Расписание
COMMENT ON COLUMN schedule.id IS 'ID записи расписания';
COMMENT ON COLUMN schedule.routedate IS 'Дата поездки';
COMMENT ON COLUMN schedule.route_id IS 'Связь с записью маршрута';
COMMENT ON COLUMN schedule.bus_id IS 'Связь с записью автобуса';
COMMENT ON COLUMN schedule.driver_id IS 'Связь с записью водителя';


--Колонки таблицы Автобусные остановки
COMMENT ON COLUMN busstop.id IS 'ID записи автобусная остановка';
COMMENT ON COLUMN busstop.namestop IS 'Название остановки';


--Колонки таблицы Тип автобуса
COMMENT ON COLUMN bustype.id IS 'ID записи тип автобуса';
COMMENT ON COLUMN bustype.nametype IS 'Наименование типа автобуса';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Автобусы
COMMENT ON CONSTRAINT fk_type ON bus IS 'Связь столбца type с таблицей Тип автобуса';


--Ключи для для таблицы Маршруты
COMMENT ON CONSTRAINT fk_startStation ON busstop IS 'Связь столбца startStation с таблицей Автобусные остановки';
COMMENT ON CONSTRAINT fk_endStation ON busstop IS 'Связь столбца endStation с таблицей Автобусные остановки';


--Ключи для для таблицы Расписание
COMMENT ON CONSTRAINT fk_route_id ON schedule IS 'Связь столбца route_id с таблицей Маршруты';
COMMENT ON CONSTRAINT fk_bus_id ON schedule IS 'Связь столбца bus_id с таблицей Автобусы';
COMMENT ON CONSTRAINT fk_driver_id ON schedule IS 'Связь столбца driver_id с таблицей Водители';

