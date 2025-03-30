--Создание таблицы Курс
CREATE TABLE IF NOT EXISTS courses (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50),
	date TIMESTAMP,
	active BOOLEAN
);


--Добавление комментариев по таблицам
COMMENT ON TABLE courses IS 'таблица Курс';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Курс
COMMENT ON COLUMN courses.id IS 'ID записи курса';
COMMENT ON COLUMN courses.name IS 'Наименование круса';
COMMENT ON COLUMN courses.date IS 'Дата начала';
COMMENT ON COLUMN courses.active IS 'Признак активности';
