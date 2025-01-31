package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Product;
import ru.innopolis.repository.impl.ProductRepositoryImpl;


@Service
public class ProductService {

    private final ProductRepositoryImpl product;

    public ProductService(ProductRepositoryImpl product) {
        this.product = product;
    }

    public Product fundProductInDate(String article) {
        return product.findByArticle(article);
    }
}
