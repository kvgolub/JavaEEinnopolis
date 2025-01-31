package ru.innopolis.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.repository.OrderRepository;


@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CREATE = """
                INSERT INTO orders (article, quantity, total, orderDate)
                VALUES (?, ?, ?, ?)
            """;

    private static final String COUNT_ORDERS = """
                SELECT
                    count(*)
                FROM orders
            """;
    private static final String AVERAGE_RECEIPT = """
                SELECT
                    round(avg(total),2)
                FROM orders
            """;

    @Override
    public void create(String article, Integer quantity, Double total, String orderDate) {
        jdbcTemplate.update(CREATE, article, quantity, total, orderDate);
    }

    @Override
    public Integer countOrders() {
        return jdbcTemplate.queryForObject(COUNT_ORDERS, Integer.class);
    }

    @Override
    public Double averageReceipt() {
        return jdbcTemplate.queryForObject(AVERAGE_RECEIPT, Double.class);
    }
}
