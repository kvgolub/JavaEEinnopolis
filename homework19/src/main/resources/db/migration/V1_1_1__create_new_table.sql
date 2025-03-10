--Создание таблицы Отзывы
CREATE TABLE IF NOT EXISTS review (
	id BIGSERIAL PRIMARY KEY,
	student_id BIGINT,
	course_id BIGINT,
	text TEXT
);


--Добавление внешних ключей для таблицы Отзывы (связь к таблице Студенты и таблице Курсы)
ALTER TABLE review
ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(id) ON UPDATE CASCADE ON DELETE CASCADE,
ADD CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--Добавление комментариев по таблицам
COMMENT ON TABLE review IS 'таблица Отзывы';


--Добавление комментариев к колонкам таблиц
--Колонки таблицы Отзывы
COMMENT ON COLUMN review.id IS 'ID записи отзыва';
COMMENT ON COLUMN review.student_id IS 'Идентификатор студента';
COMMENT ON COLUMN review.course_id IS 'Идентификатор курса';
COMMENT ON COLUMN review.text IS 'Текст отзыва';


--Добавление комментариев к внешним ключам таблиц
--Ключи для для таблицы Отзывы
COMMENT ON CONSTRAINT fk_student ON review IS 'Связь столбца studentId с таблицей student';
COMMENT ON CONSTRAINT fk_course ON review IS 'Связь столбца courseId с таблицей course';