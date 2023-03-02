package com.gstarkma.s3pdfsimulator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    
    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        // Code to retrieve all products from a database or other source
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", 10.00));
        products.add(new Product("Product 2", 15.00));
        products.add(new Product("Product 3", 20.00));
        return products;
    }
}