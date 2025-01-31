package ru.innopolis.controller;

import org.springframework.web.bind.annotation.*;
import ru.innopolis.entity.Product;
import ru.innopolis.service.ProductService;


@RestController
@RequestMapping("api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8081/api/v1/find/123-bear
    @GetMapping("/find/{article}")
    public Product findProduct(@PathVariable String article) {
        return productService.fundProductInDate(article);
    }
}
