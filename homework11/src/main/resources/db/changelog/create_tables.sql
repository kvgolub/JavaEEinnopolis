-- База данных Хранение пользовательских текстовых заметок (user_textnote)

-- Создание таблицы заметки
CREATE TABLE IF NOT EXISTS note(
	id INTEGER PRIMARY KEY,
	noteDate TIMESTAMP,
	theme VARCHAR(50),
	textnote TEXT
);
