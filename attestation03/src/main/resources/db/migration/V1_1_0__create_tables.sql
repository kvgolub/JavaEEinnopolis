--Создание таблицы Автобусы
CREATE TABLE IF NOT EXISTS bus (
	id SERIAL PRIMARY KEY,
	type INTEGER,
	model VARCHAR(50),
	reg_number VARCHAR(6),
	passenger_capacity INTEGER,
	price NUMERIC,
	deleted BOOLEAN DEFAULT false
);


--Создание таблицы Маршруты
CREATE TABLE IF NOT EXISTS route (
	id SERIAL PRIMARY KEY,
	number varchar(5),
	start_station INTEGER,
	end_station INTEGER,
	quantity_station INTEGER,
	passenger_flow INTEGER,
	deleted BOOLEAN DEFAULT false
);


--Создание таблицы Водители
CREATE TABLE IF NOT EXISTS driver (
	id SERIAL PRIMARY KEY,
	surname VARCHAR(30),
	name VARCHAR(30),
	age INTEGER,
	deleted BOOLEAN DEFAULT false
);


--Создание таблицы Расписание (связь многие-ко-многим)
CREATE TABLE IF NOT EXISTS schedule (
	id SERIAL PRIMARY KEY,
	route_date TIMESTAMP,
	route_id INTEGER,
	bus_id INTEGER,
	driver_id INTEGER,
	deleted BOOLEAN DEFAULT false
);


--Создание таблицы Автобусные остановки (связь один-ко-многим)
CREATE TABLE IF NOT EXISTS bus_stop (
	id SERIAL PRIMARY KEY,
	name_stop VARCHAR(50),
	deleted BOOLEAN DEFAULT false
);


--Создание таблицы Тип автобуса (связь один-ко-многим)
CREATE TABLE IF NOT EXISTS bus_type (
	id SERIAL PRIMARY KEY,
	name_type VARCHAR(50),
	deleted BOOLEAN DEFAULT false
);


--Добавление внешнего ключа для таблицы Автобусы (к таблице Тип автобуса)
ALTER TABLE bus
ADD CONSTRAINT fk_type FOREIGN KEY (type) REFERENCES bus_type(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление внешних ключей для таблицы Маршруты (к таблице Автобусные остановки)
ALTER TABLE route
ADD CONSTRAINT fk_start_station FOREIGN KEY (start_station) REFERENCES bus_stop(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_end_station FOREIGN KEY (end_station) REFERENCES bus_stop(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление внешних ключей для таблицы Расписание (к таблицам Маршруты, Автобусы, Водители)
ALTER TABLE schedule
ADD CONSTRAINT fk_route_id FOREIGN KEY (route_id) REFERENCES route(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_bus_id FOREIGN KEY (bus_id) REFERENCES bus(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_driver_id FOREIGN KEY (driver_id) REFERENCES driver(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление комментария о БД
COMMENT ON DATABASE bus_station IS 'база данных Автобусный парк';


--Добавление комментариев по таблицам
COMMENT ON TABLE bus IS 'таблица Автобусы';
COMMENT ON TABLE route IS 'таблица Маршруты';
COMMENT ON TABLE driver IS 'таблица Водители';
COMMENT ON TABLE schedule IS 'таблица Расписание (связь многие-ко-многим)';
COMMENT ON TABLE bus_stop IS 'таблица Автобусные остановки (связь один-ко-многим)';
COMMENT ON TABLE bus_type IS 'таблица Тип автобуса (связь один-ко-многим)';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Автобусы
COMMENT ON COLUMN bus.id IS 'ID записи автобуса';
COMMENT ON COLUMN bus.type IS 'Тип автобуса';
COMMENT ON COLUMN bus.model IS 'Марка автобуса';
COMMENT ON COLUMN bus.reg_number IS 'Регистрационный номер автобуса';
COMMENT ON COLUMN bus.passenger_capacity IS 'Пассажировместимость автобуса';
COMMENT ON COLUMN bus.price IS 'Стоимость автобуса';


--Колонки таблицы Маршруты
COMMENT ON COLUMN route.id IS 'ID записи маршрута';
COMMENT ON COLUMN route.number IS 'Номер маршрута';
COMMENT ON COLUMN route.start_station IS 'Остановка отправления на маршруте';
COMMENT ON COLUMN route.end_station IS 'Остановка прибытия на маршруте';
COMMENT ON COLUMN route.quantity_station IS 'Количество остановок на маршруте';
COMMENT ON COLUMN route.passenger_flow IS 'Пассажиропоток на маршруте';


--Колонки таблицы Водители
COMMENT ON COLUMN driver.id IS 'ID записи водителя';
COMMENT ON COLUMN driver.surname IS 'Фамилия водителя';
COMMENT ON COLUMN driver.name IS 'Имя водителя';
COMMENT ON COLUMN driver.age IS 'Возраст водителя';


--Колонки таблицы Расписание
COMMENT ON COLUMN schedule.id IS 'ID записи расписания';
COMMENT ON COLUMN schedule.route_date IS 'Дата поездки';
COMMENT ON COLUMN schedule.route_id IS 'Связь с записью маршрута';
COMMENT ON COLUMN schedule.bus_id IS 'Связь с записью автобуса';
COMMENT ON COLUMN schedule.driver_id IS 'Связь с записью водителя';


--Колонки таблицы Автобусные остановки
COMMENT ON COLUMN bus_stop.id IS 'ID записи автобусная остановка';
COMMENT ON COLUMN bus_stop.name_stop IS 'Название остановки';


--Колонки таблицы Тип автобуса
COMMENT ON COLUMN bus_type.id IS 'ID записи тип автобуса';
COMMENT ON COLUMN bus_type.name_type IS 'Наименование типа автобуса';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Автобусы
COMMENT ON CONSTRAINT fk_type ON bus IS 'Связь столбца type с таблицей Тип автобуса';


--Ключи для для таблицы Маршруты
COMMENT ON CONSTRAINT fk_start_station ON route IS 'Связь столбца startStation с таблицей Автобусные остановки';
COMMENT ON CONSTRAINT fk_end_station ON route IS 'Связь столбца endStation с таблицей Автобусные остановки';


--Ключи для для таблицы Расписание
COMMENT ON CONSTRAINT fk_route_id ON schedule IS 'Связь столбца route_id с таблицей Маршруты';
COMMENT ON CONSTRAINT fk_bus_id ON schedule IS 'Связь столбца bus_id с таблицей Автобусы';
COMMENT ON CONSTRAINT fk_driver_id ON schedule IS 'Связь столбца driver_id с таблицей Водители';
