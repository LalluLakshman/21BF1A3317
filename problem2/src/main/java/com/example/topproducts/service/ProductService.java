package com.example.topproducts.service;

import com.example.topproducts.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final RestTemplate restTemplate;

    public ProductService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Product> getTopProducts(String category, int n, int page, String sort, String order) {
        // Mock API URLs
        List<String> apiUrls = List.of(
                "https://api.company1.com/products?category=" + category,
                "https://api.company2.com/products?category=" + category
                // Add other company URLs
        );

        List<Product> allProducts = new ArrayList<>();
        for (String url : apiUrls) {
            Product[] products = restTemplate.getForObject(url, Product[].class);
            if (products != null) {
                for (Product product : products) {
                    product.setId(UUID.randomUUID().toString());
                }
                allProducts.addAll(List.of(products));
            }
        }

        // Sort and paginate
        if (sort != null) {
            allProducts = allProducts.stream()
                    .sorted((p1, p2) -> {
                        int comparison = 0;
                        switch (sort) {
                            case "rating":
                                comparison = Double.compare(p1.getRating(), p2.getRating());
                                break;
                            case "price":
                                comparison = Double.compare(p1.getPrice(), p2.getPrice());
                                break;
                            case "company":
                                comparison = p1.getCompany().compareTo(p2.getCompany());
                                break;
                            case "discount":
                                comparison = Double.compare(p1.getDiscount(), p2.getDiscount());
                                break;
                        }
                        return "desc".equalsIgnoreCase(order) ? -comparison : comparison;
                    })
                    .collect(Collectors.toList());
        }

        int start = (page - 1) * n;
        int end = Math.min(start + n, allProducts.size());
        return allProducts.subList(start, end);
    }
}

