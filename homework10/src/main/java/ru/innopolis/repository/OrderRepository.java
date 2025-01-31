package ru.innopolis.repository;


public interface OrderRepository {

    void create(String article, Integer quantity, Double total, String orderDate);
    Integer countOrders();
    Double averageReceipt();
}
