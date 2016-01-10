package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;

public interface GetProductDetailUseCase {
    void execute(String productId, Callback<Product> callback);
}
