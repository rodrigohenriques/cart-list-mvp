package com.github.rodrigohenriques.domain.repository;

import com.github.rodrigohenriques.domain.entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductList();
    Product getProductDetail(int productId);
}
