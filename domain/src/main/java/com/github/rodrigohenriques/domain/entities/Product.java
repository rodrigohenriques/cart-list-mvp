package com.github.rodrigohenriques.domain.entities;

public class Product {
    public final String identifier;
    public final String name;
    public final String imageUrl;
    public final String description;
    public final long price;

    public Product(String identifier, String name, String imageUrl, String description, long price) {
        this.identifier = identifier;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }
}
