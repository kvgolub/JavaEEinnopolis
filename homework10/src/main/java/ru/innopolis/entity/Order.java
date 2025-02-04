package ru.innopolis.entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private Integer id;
    private String article;
    private Integer quantity;
    private Double total;
    private String orderDate;
}
