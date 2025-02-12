--Создание таблицы Студент
CREATE TABLE IF NOT EXISTS student (
	id BIGINT PRIMARY KEY,
	surname VARCHAR(50),
	name VARCHAR(50),
	patronymic VARCHAR(50),
	email VARCHAR(50)
);


--Создание таблицы Курс
CREATE TABLE IF NOT EXISTS course (
	id BIGINT PRIMARY KEY,
	name VARCHAR(50)
);


--Создание соединительной таблицы Студенты - Курсы
CREATE TABLE IF NOT EXISTS studentsOnCourses (
	id BIGINT PRIMARY KEY,
	studentId BIGINT,
	courseId BIGINT
);


--Добавление внешних ключей для таблицы Студенты на Курсах (связь к таблицам Студенты, Курсы)
ALTER TABLE studentsOnCourses
ADD CONSTRAINT fk_student FOREIGN KEY (studentId) REFERENCES student(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_course FOREIGN KEY (courseId) REFERENCES course(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление комментария о БД
COMMENT ON DATABASE students IS 'база данных Студенты';


--Добавление комментариев по таблицам
COMMENT ON TABLE student IS 'таблица Студент';
COMMENT ON TABLE course IS 'таблица Курс';
COMMENT ON TABLE studentsOnCourses IS 'таблица Студенты на Курсах';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Студент
COMMENT ON COLUMN student.id IS 'ID записи студента';
COMMENT ON COLUMN student.surname IS 'Фамилия студента';
COMMENT ON COLUMN student.name IS 'Имя студента';
COMMENT ON COLUMN student.patronymic IS 'Отчество студента';
COMMENT ON COLUMN student.email IS 'Электронная почта студента';


--Колонки таблицы Курс
COMMENT ON COLUMN course.id IS 'ID записи курса';
COMMENT ON COLUMN course.name IS 'Наименование круса';


--Колонки таблицы Студенты на Курсах
COMMENT ON COLUMN studentsOnCourses.id IS 'ID записи студена на курсе';
COMMENT ON COLUMN studentsOnCourses.studentId IS 'Идентификатор студента';
COMMENT ON COLUMN studentsOnCourses.courseId IS 'Идентификатор курса';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Студенты на Курсах
COMMENT ON CONSTRAINT fk_student ON studentsOnCourses IS 'Связь столбца studentId с таблицей student';
COMMENT ON CONSTRAINT fk_course ON studentsOnCourses IS 'Связь столбца courseId с таблицей course';
