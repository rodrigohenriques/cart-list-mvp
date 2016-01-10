package com.github.rodrigohenriques.domain.repository;

import com.github.rodrigohenriques.domain.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    // TODO: fix this exceptions
    List<Product> getCartList() throws Exception;
    Product getProductDetail(String productId) throws IOException;
}
