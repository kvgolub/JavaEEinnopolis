CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);


CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);


CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);


----Добавление комментариев по таблицам
--COMMENT ON TABLE users IS 'таблица Пользователи';
--COMMENT ON TABLE authorities IS 'таблица Права доступа';
--
--
----Добавление комментариев к колонкам таблиц
----Колонки таблицы Пользователи
--COMMENT ON COLUMN users.username IS 'Имя пользователя';
--COMMENT ON COLUMN users.password IS 'Пароль пользователя';
--COMMENT ON COLUMN users.enabled IS 'Действующая учетная запись';
--
----Колонки таблицы Права доступа
--COMMENT ON COLUMN authorities.username IS 'Имя пользователя';
--COMMENT ON COLUMN authorities.authority IS 'Роль';
--
--
----Добавление комментариев к внешним ключам таблиц
----Ключи для для таблицы Права доступа
--COMMENT ON CONSTRAINT fk_authorities_users ON authorities IS 'Связь столбца username с таблицей users';
