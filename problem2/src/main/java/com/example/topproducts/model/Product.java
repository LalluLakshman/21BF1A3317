package com.example.topproducts.model;

public class Product {
    private String id;
    private String name;
    private String category;
    private double price;
    private double rating;
    private String company;
    private double discount;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
}

