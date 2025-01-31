# База данных Интернет-магазина игрушек (toy_shop)

# Создание таблицы товаров
CREATE TABLE IF NOT EXISTS products(
	id SERIAL PRIMARY KEY COMMENT 'Идентификатор товара',
	article VARCHAR(20) COMMENT 'Артикул товара',
	price DECIMAL(10,2) COMMENT 'Цена товара'
);

# Создание таблицы заказов
CREATE TABLE IF NOT EXISTS orders(
	id SERIAL PRIMARY KEY COMMENT 'Идентификатор заказа',
	article VARCHAR(20) COMMENT 'Артикул товара',
	quantity INT COMMENT 'Количество',
	total DECIMAL(10,2) COMMENT 'Сумма',
	orderDate DATETIME COMMENT 'Дата и время заказа'
);
