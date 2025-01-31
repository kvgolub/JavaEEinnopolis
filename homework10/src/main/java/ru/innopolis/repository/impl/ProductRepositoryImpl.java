package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.innopolis.entity.Product;
import ru.innopolis.repository.ProductRepository;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String FIND_BY_ARTICLE = """
                SELECT
                    *
                FROM products
                WHERE article = ?
            """;

    @Override
    public Product findByArticle(String article) {
        return jdbcTemplate.queryForObject(FIND_BY_ARTICLE, productRowMapper, article);
    }

    private static final RowMapper<Product> productRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String article = row.getString("article");
        Double price = row.getDouble("price");

        return new Product(id, article, price);
    };
}
