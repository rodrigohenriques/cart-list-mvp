package com.github.rodrigohenriques.picnic.presenter;

import com.github.rodrigohenriques.data.entities.ListMarshaller;
import com.github.rodrigohenriques.data.entities.Marshaller;
import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.interactor.Callback;
import com.github.rodrigohenriques.domain.interactor.GetCartListUseCase;
import com.github.rodrigohenriques.picnic.ui.custom.CurrencyFormatter;
import com.github.rodrigohenriques.picnic.view.CartListView;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

import java.util.List;

import javax.inject.Inject;

public class CartListPresenterImpl implements CartListPresenter {

    private static final long CACHE_TIME = 30000;

    private GetCartListUseCase mGetCartListUseCase;
    private CurrencyFormatter mCurrencyFormatter;
    private CartListView mCartListView;
    private List<Product> mProductsCache;
    private long mCacheExpirationTime;

    @Inject
    public CartListPresenterImpl(GetCartListUseCase getCartListUseCase, CurrencyFormatter currencyFormatter) {
        this.mGetCartListUseCase = getCartListUseCase;
        this.mCurrencyFormatter = currencyFormatter;
    }

    private void queryData() {
        if (shouldQueryData()) {
            showLoading();

            mGetCartListUseCase.execute(new Callback<List<Product>>() {
                @Override
                public void onSuccess(List<Product> products) {
                    mProductsCache = products;
                    mCacheExpirationTime = System.currentTimeMillis() + CACHE_TIME;

                    showData(products);
                }

                @Override
                public void onException(Exception e) {
                    showError(DEFAULT_ERROR_MESSAGE);
                }

                @Override
                public void onPostExecute() {
                    hideLoading();
                }
            });
        }
    }

    private void showLoading() {
        if (hasViewAttached()) {
            mCartListView.showLoading();
        }
    }

    private void hideLoading() {
        if (hasViewAttached()) {
            mCartListView.hideLoading();
        }
    }

    private boolean hasViewAttached() {
        return mCartListView != null;
    }

    private void showError(String message) {
        if (hasViewAttached()) {
            mCartListView.showError(message);
        }
    }

    @Override
    public void attachView(CartListView view) {
        mCartListView = view;

        if (hasValidCache()) {
            showData(mProductsCache);
        } else {
            queryData();
        }
    }

    @Override
    public void loadCartList() {

    }

    @Override
    public void clickedAt(Product product, int position) {
        if (hasViewAttached()) {
            mCartListView.openProductDetail(product.identifier);
        }
    }

    private boolean shouldQueryData() {
        return !hasValidCache();
    }

    private boolean hasValidCache() {
        return mProductsCache != null &&
                System.currentTimeMillis() < mCacheExpirationTime;
    }

    private void showData(List<Product> products) {
        if (hasViewAttached()) {
            ListMarshaller<Product, ProductViewModel> marshaller = new ListMarshaller<>(new Marshaller<Product, ProductViewModel>() {
                @Override
                public ProductViewModel marshal(Product product) {
                    return new ProductViewModel(product, mCurrencyFormatter);
                }
            });

            List<ProductViewModel> productViewModels = marshaller.marshal(products);

            mCartListView.showData(productViewModels);
        }
    }
}
