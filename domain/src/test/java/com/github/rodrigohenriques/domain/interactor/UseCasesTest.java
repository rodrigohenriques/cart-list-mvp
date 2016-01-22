package com.github.rodrigohenriques.domain.interactor;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UseCasesTest {
    private ProductRepository mProductRepository;
    private List<Product> mSimpleListOfProducts;
    private Object mResult;
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private SecondaryThreadExecutor mUiThreadExecutor;
    private final Product mOnion;
    private final Product mBanana;

    public UseCasesTest() throws IOException {
        mSimpleListOfProducts = new ArrayList<>();

        mOnion = new Product("1", "Onion", null, "lorem ipsum", 100);
        mBanana = new Product("2", "Banana", null, "lorem ipsum", 200);

        mSimpleListOfProducts.add(mOnion);
        mSimpleListOfProducts.add(mBanana);

        mUiThreadExecutor = new SecondaryThreadExecutor();
        mProductRepository = mock(ProductRepository.class);

        when(mProductRepository.getCartList()).thenReturn(mSimpleListOfProducts);

        when(mProductRepository.getProductDetail(mOnion.getIdentifier())).thenReturn(mOnion);
        when(mProductRepository.getProductDetail(mBanana.getIdentifier())).thenReturn(mBanana);
    }

    @Before
    public void setUp() {
        mResult = null;
    }

    @Test
    public void testGetProducts() throws InterruptedException {
        GetCartListUseCase getCartListUseCase = new GetCartListUseCaseImpl(new SecondaryThreadExecutor(), mProductRepository);

        getCartListUseCase.execute(mDefaultCallback);

        mCountDownLatch.await(2, TimeUnit.SECONDS);

        assertEquals(mResult, mSimpleListOfProducts);
    }

    @Test
    public void testGetProductDetail() throws InterruptedException {
        GetProductDetailUseCase getProductDetailUseCase = new GetProductDetailUseCaseImpl(mUiThreadExecutor, mProductRepository);

        getProductDetailUseCase.execute(mBanana.getIdentifier(), mDefaultCallback);

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