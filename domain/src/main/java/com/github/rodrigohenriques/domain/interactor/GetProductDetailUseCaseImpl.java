package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.repository.ProductRepository;

import java.io.IOException;

import javax.inject.Inject;

public class GetProductDetailUseCaseImpl extends AbstractUseCase<Integer, Product> implements GetProductDetailUseCase {

    ProductRepository mProductRepository;

    @Inject
    public GetProductDetailUseCaseImpl(UiThreadExecutor uiThreadExecutor, ProductRepository productRepository) {
        super(uiThreadExecutor);

        mProductRepository = productRepository;
    }

    @Override
    public void execute(int productId, Callback<Product> callback) {
        executeAsync(productId, callback);
    }

    @Override
    protected Product executeOnBackground(Integer productId) throws IOException {
        return mProductRepository.getProductDetail(productId);
    }
}
