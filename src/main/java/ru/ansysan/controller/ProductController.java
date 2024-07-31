package ru.ansysan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ansysan.Service.ProductFilter;
import ru.ansysan.Service.ProductService;
import ru.ansysan.entity.Product;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductFilter productFilter;

//    Эндпоинт для поиска продуктов
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
//        return ResponseEntity.ok(products);
        return productFilter.Filter(products);
    }
}