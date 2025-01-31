package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.entity.Product;
import ru.innopolis.repository.impl.OrderRepositoryImpl;
import ru.innopolis.repository.impl.ProductRepositoryImpl;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class OrderService {

    private final OrderRepositoryImpl order;
    private final ProductRepositoryImpl product;

    public OrderService(OrderRepositoryImpl order, ProductRepositoryImpl product) {
        this.order = order;
        this.product = product;
    }

    public Integer newOrder(String article, Integer quantity) {
        Product findProductInData = product.findByArticle(article);
        Double priceProduct = findProductInData.getPrice();

        if (priceProduct > 0) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = formatter.format(date);

            order.create(article, quantity, priceProduct * quantity, strDate);
            return 1;
        } else {
            return 0;
        }
    }

    public Integer countOrder() {
        return order.countOrders();
    }

    public Double averageReceipt() {
        return order.averageReceipt();
    }
}
