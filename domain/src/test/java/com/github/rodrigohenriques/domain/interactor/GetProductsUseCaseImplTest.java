package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetProductsUseCaseImplTest {

    ProductRepository mProductRepository;
    List<Product> mSimpleListOfProducts;
    List<Product> mResult;

    CountDownLatch mCountDownLatch = new CountDownLatch(1);

    public GetProductsUseCaseImplTest() {
        mSimpleListOfProducts = new ArrayList<>();

        mSimpleListOfProducts.add(new Product(1, "Onion", null, "lorem ipsum", 100));
        mSimpleListOfProducts.add(new Product(2, "Banana", null, "lorem ipsum", 200));
    }

    @Before
    public void setUp() throws Exception {
        mProductRepository = mock(ProductRepository.class);

        when(mProductRepository.getProductList()).thenReturn(mSimpleListOfProducts);
    }

    @Test
    public void test() throws InterruptedException {
        GetProductsUseCase getProductsUseCase = new GetProductsUseCaseImpl(new SecondaryThreadExecutor(), mProductRepository);

        getProductsUseCase.execute(new Callback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                mResult = products;
                mCountDownLatch.countDown();
            }

            @Override
            public void onException(Exception e) {
                fail();
            }

            @Override
            public void onPostExecute() {
                // do nothing
            }
        });

        mCountDownLatch.await(2, TimeUnit.SECONDS);

        assertEquals(mResult, mSimpleListOfProducts);
    }
}