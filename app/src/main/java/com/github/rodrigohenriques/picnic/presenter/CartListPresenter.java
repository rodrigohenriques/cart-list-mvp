package com.github.rodrigohenriques.picnic.presenter;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.picnic.view.CartListView;

public interface CartListPresenter extends Presenter {
    void attachView(CartListView view);
    void loadCartList();
    void clickedAt(Product product, int position);
}
