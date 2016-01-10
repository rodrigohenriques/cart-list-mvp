package com.github.rodrigohenriques.domain.repository;

import com.github.rodrigohenriques.domain.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    List<Product> getCartList() throws IOException;
    Product getProductDetail(String productId) throws IOException;
}
