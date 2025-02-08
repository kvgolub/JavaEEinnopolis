--Создание таблицы Студент
CREATE TABLE IF NOT EXISTS student(
	id BIGINT PRIMARY KEY,
	surname VARCHAR(50),
	name VARCHAR(50),
	patronymic VARCHAR(50),
	email VARCHAR(50),
	course _VARCHAR
);

--Добавление комментария о БД
COMMENT ON DATABASE students IS 'база данных Студенты';


--Добавление комментариев по таблицам
COMMENT ON TABLE student IS 'таблица Студент';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Студент
COMMENT ON COLUMN student.id IS 'ID записи студента';
COMMENT ON COLUMN student.surname IS 'Фамилия студента';
COMMENT ON COLUMN student.name IS 'Имя студента';
COMMENT ON COLUMN student.patronymic IS 'Отчество студента';
COMMENT ON COLUMN student.email IS 'электронная почта студента';
COMMENT ON COLUMN student.course IS 'Список курсов';
