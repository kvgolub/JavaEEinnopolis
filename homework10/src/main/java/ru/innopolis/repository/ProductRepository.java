package ru.innopolis.repository;

import ru.innopolis.entity.Product;


public interface ProductRepository {

    Product findByArticle(String article);
}
