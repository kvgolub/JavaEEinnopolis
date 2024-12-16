package org.example;


public class App {
    public static void main(String[] args) {
        Product apple = new Product(1, "Food", 10.50f, 20L);
        Product pear = new Product(2, "Food", 15.90f, 5L);
        Product pencil = new Product(3, "Stationery", 20f, 35L);
        Product book = new Product();

        System.out.println(apple);
        System.out.println(pear);
        System.out.println(pencil);
        System.out.println(book);
    }
}