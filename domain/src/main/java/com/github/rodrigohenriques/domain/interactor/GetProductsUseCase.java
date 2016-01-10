package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;

import java.util.List;

public interface GetProductsUseCase extends UseCase {
    void execute(Callback<List<Product>> products);
}
