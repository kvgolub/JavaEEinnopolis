package ru.innopolis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.Order;
import ru.innopolis.service.OrderService;


@RestController
@RequestMapping("api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // http://localhost:8081/api/v1/create
    @PostMapping("create")
    public ResponseEntity createOrder(@RequestBody Order order) {
        try {
            if (order.getQuantity() > 0) {
                Integer statusOrder = orderService.newOrder(order.getArticle(), order.getQuantity());
                if (statusOrder == 1) {
                    return ResponseEntity.ok(HttpStatus.OK);
                } else if (statusOrder == 0) {
                    return ResponseEntity.badRequest().body("Сумма заказа должна быть быть больше нуля");
                }
            } else {
                //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                return ResponseEntity.badRequest().body("Количество товара должно быть больше нуля");
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Введен некорректный артикул");
        }
    }
}
