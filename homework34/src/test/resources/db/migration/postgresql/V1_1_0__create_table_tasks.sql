--Создание таблицы Задачи
CREATE TABLE IF NOT EXISTS tasks (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50),
    description TEXT,
	created_at TIMESTAMP
);


--Добавление комментария о БД
COMMENT ON DATABASE my_tasks IS 'база данных Мои_задачи';


--Добавление комментариев по таблицам
COMMENT ON TABLE tasks IS 'таблица Задачи';


--Добавление комментариев к колонкам таблицы
--Колонки таблицы Задачи
COMMENT ON COLUMN tasks.id IS 'ID записи задачи';
COMMENT ON COLUMN tasks.name IS 'Название задачи';
COMMENT ON COLUMN tasks.description IS 'Описание задача';
COMMENT ON COLUMN tasks.created_at IS 'Время создания';
