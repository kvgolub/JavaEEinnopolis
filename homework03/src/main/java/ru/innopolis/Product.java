package ru.innopolis;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    private Integer id;
    private String name;
    private Float price;
    private Long quantity;
    private String measure;
}
