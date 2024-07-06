package com.example.fooddelivery;

public class FoodItem {
    private String name;
    private String description;
    private double price;
    private int imageResourceId;

    public FoodItem(String name, String description, double price, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
