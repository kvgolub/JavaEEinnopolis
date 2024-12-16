package org.example;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Product {
    private Integer id;
    private String description;
    private Float price;
    private Long quantity;
}