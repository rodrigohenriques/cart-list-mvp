package com.github.rodrigohenriques.domain.entities;

public class Product {
    private String identifier;
    private String name;
    private String imageUrl;
    private String description;
    private long price;

    public Product(String identifier, String name, String imageUrl, long price) {
        this.identifier = identifier;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product(String identifier, String name, String imageUrl, String description, long price) {
        this.identifier = identifier;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long getPrice() {
        return price;
    }
}
