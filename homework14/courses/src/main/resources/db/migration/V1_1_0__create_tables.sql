--Создание таблицы Курс
CREATE TABLE IF NOT EXISTS course (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50),
	date TIMESTAMP,
	active BOOLEAN
);


--Добавление комментария о БД
COMMENT ON DATABASE courses IS 'база данных Курсы';


--Добавление комментариев по таблице
COMMENT ON TABLE course IS 'таблица Курс';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Курс
COMMENT ON COLUMN course.id IS 'ID записи курса';
COMMENT ON COLUMN course.name IS 'Наименование круса';
COMMENT ON COLUMN course.date IS 'Дата начала';
COMMENT ON COLUMN course.active IS 'Признак активности';
