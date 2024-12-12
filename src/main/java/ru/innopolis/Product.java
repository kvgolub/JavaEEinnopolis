package ru.innopolis;


public class Product {

    private Integer id;
    private String description;
    private Float price;
    private Long quantity;

    public Product() {
    }

    public Product(Integer id, String description, Float price, Long quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Float getPrice() {
        return this.price;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Product(id=" + this.getId() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", quantity=" + this.getQuantity() + ")";
    }
}