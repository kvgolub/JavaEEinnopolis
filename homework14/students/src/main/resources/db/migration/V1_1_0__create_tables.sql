--Создание таблицы Студент
CREATE TABLE IF NOT EXISTS student (
	id BIGSERIAL PRIMARY KEY,
	surname VARCHAR(50),
	name VARCHAR(50),
	patronymic VARCHAR(50),
	email VARCHAR(50)
);


--Создание соединительной таблицы Студенты - Курсы
CREATE TABLE IF NOT EXISTS studentsOnCourses (
	id BIGSERIAL PRIMARY KEY,
	studentId BIGINT,
	courseId BIGINT
);


--Добавление внешних ключей для таблицы Студенты на Курсах (связь к таблице Студенты)
ALTER TABLE studentsOnCourses
ADD CONSTRAINT fk_student FOREIGN KEY (studentId) REFERENCES student(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление комментария о БД
COMMENT ON DATABASE students IS 'база данных Студенты';


--Добавление комментариев по таблицам
COMMENT ON TABLE student IS 'таблица Студент';
COMMENT ON TABLE studentsOnCourses IS 'таблица Студенты на Курсах';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Студент
COMMENT ON COLUMN student.id IS 'ID записи студента';
COMMENT ON COLUMN student.surname IS 'Фамилия студента';
COMMENT ON COLUMN student.name IS 'Имя студента';
COMMENT ON COLUMN student.patronymic IS 'Отчество студента';
COMMENT ON COLUMN student.email IS 'Электронная почта студента';


--Колонки таблицы Студенты на Курсах
COMMENT ON COLUMN studentsOnCourses.id IS 'ID записи студена на курсе';
COMMENT ON COLUMN studentsOnCourses.studentId IS 'Идентификатор студента';
COMMENT ON COLUMN studentsOnCourses.courseId IS 'Идентификатор курса';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Студенты на Курсах
COMMENT ON CONSTRAINT fk_student ON studentsOnCourses IS 'Связь столбца studentId с таблицей student';
