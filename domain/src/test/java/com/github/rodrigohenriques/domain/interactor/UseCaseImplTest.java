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

public class UseCaseImplTest {

    private final Product mOnion;
    private final Product mBanana;
    ProductRepository mProductRepository;
    List<Product> mSimpleListOfProducts;
    Object mResult;

    CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private SecondaryThreadExecutor mUiThreadExecutor;

    public UseCaseImplTest() {
        mSimpleListOfProducts = new ArrayList<>();

        mOnion = new Product(1, "Onion", null, "lorem ipsum", 100);
        mBanana = new Product(2, "Banana", null, "lorem ipsum", 200);

        mSimpleListOfProducts.add(mOnion);
        mSimpleListOfProducts.add(mBanana);

        mUiThreadExecutor = new SecondaryThreadExecutor();
        mProductRepository = mock(ProductRepository.class);

        when(mProductRepository.getProductList()).thenReturn(mSimpleListOfProducts);

        when(mProductRepository.getProductDetail(mOnion.identifier)).thenReturn(mOnion);
        when(mProductRepository.getProductDetail(mBanana.identifier)).thenReturn(mBanana);
    }

    @Before
    public void setUp() {
        mResult = null;
    }

    @Test
    public void testGetProducts() throws InterruptedException {
        GetProductsUseCase getProductsUseCase = new GetProductsUseCaseImpl(new SecondaryThreadExecutor(), mProductRepository);

        getProductsUseCase.execute(mDefaultCallback);

        mCountDownLatch.await(2, TimeUnit.SECONDS);

        assertEquals(mResult, mSimpleListOfProducts);
    }

    @Test
    public void testGetProductDetail() throws InterruptedException {
        GetProductDetailUseCase getProductDetailUseCase = new GetProductDetailUseCaseImpl(mUiThreadExecutor, mProductRepository);

        getProductDetailUseCase.execute(mBanana.identifier, mDefaultCallback);

        mCountDownLatch.await(2, TimeUnit.SECONDS);

        assertEquals(mResult, mBanana);
    }

    final Callback mDefaultCallback = new Callback() {
        @Override
        public void onSuccess(Object result) {
            mResult = result;
        }

        @Override
        public void onException(Exception e) {
            fail();
        }

        @Override
        public void onPostExecute() {
            // do nothing
        }
    };
}