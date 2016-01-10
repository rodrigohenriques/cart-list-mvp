package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class GetProductsUseCaseImpl extends AbstractUseCase<Void, List<Product>> implements GetProductsUseCase {
    ProductRepository mProductRepository;

    @Inject
    public GetProductsUseCaseImpl(UiThreadExecutor uiThreadExecutor, ProductRepository productRepository) {
        super(uiThreadExecutor);

        mProductRepository = productRepository;
    }

    @Override
    public void execute(Callback<List<Product>> callback) {
        executeAsync(null, callback);
    }

    @Override
    protected List<Product> executeOnBackground(Void aVoid) throws IOException {
        return mProductRepository.getProductList();
    }
}
