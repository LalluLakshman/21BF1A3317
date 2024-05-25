package com.example.topproducts.controller;

import com.example.topproducts.model.Product;
import com.example.topproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/categories/{categoryname}/products")
    public List<Product> getTopProducts(
            @PathVariable String categoryname,
            @RequestParam int n,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order
    ) {
        return productService.getTopProducts(categoryname, n, page, sort, order);
    }
}

