--Создание таблицы Студент
CREATE TABLE IF NOT EXISTS students (
	id BIGSERIAL PRIMARY KEY,
	surname VARCHAR(50),
	name VARCHAR(50),
	patronymic VARCHAR(50),
	age INTEGER,
	email VARCHAR(50)
);


--Создание таблицы Курс
CREATE TABLE IF NOT EXISTS courses (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50),
	date TIMESTAMP,
	active BOOLEAN
);


--Создание соединительной таблицы Студенты - Курсы
CREATE TABLE IF NOT EXISTS students_courses (
	id BIGSERIAL PRIMARY KEY,
	student_id BIGINT,
	course_id BIGINT
);


--Добавление внешних ключей для таблицы Студенты на Курсах (связь к таблице Студенты)
ALTER TABLE students_courses
ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление комментария о БД
COMMENT ON DATABASE studies IS 'база данных Учеба';


--Добавление комментариев по таблицам
COMMENT ON TABLE students IS 'таблица Студент';
COMMENT ON TABLE courses IS 'таблица Курс';
COMMENT ON TABLE students_courses IS 'таблица Студенты на Курсах';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Студент
COMMENT ON COLUMN students.id IS 'ID записи студента';
COMMENT ON COLUMN students.surname IS 'Фамилия студента';
COMMENT ON COLUMN students.name IS 'Имя студента';
COMMENT ON COLUMN students.patronymic IS 'Отчество студента';
COMMENT ON COLUMN students.age IS 'Возраст студента';
COMMENT ON COLUMN students.email IS 'Электронная почта студента';


--Колонки таблицы Курс
COMMENT ON COLUMN courses.id IS 'ID записи курса';
COMMENT ON COLUMN courses.name IS 'Наименование круса';
COMMENT ON COLUMN courses.date IS 'Дата начала';
COMMENT ON COLUMN courses.active IS 'Признак активности';


--Колонки таблицы Студенты на Курсах
COMMENT ON COLUMN students_courses.id IS 'ID записи студена на курсе';
COMMENT ON COLUMN students_courses.student_id IS 'Идентификатор студента';
COMMENT ON COLUMN students_courses.course_id IS 'Идентификатор курса';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Студенты на Курсах
COMMENT ON CONSTRAINT fk_student ON students_courses IS 'Связь столбца studentId с таблицей student';
COMMENT ON CONSTRAINT fk_course ON students_courses IS 'Связь столбца courseId с таблицей course';
