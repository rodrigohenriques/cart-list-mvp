package com.github.rodrigohenriques.domain.repository;

import com.github.rodrigohenriques.domain.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    List<Product> getProductList() throws IOException;
    Product getProductDetail(int productId) throws IOException;
}
