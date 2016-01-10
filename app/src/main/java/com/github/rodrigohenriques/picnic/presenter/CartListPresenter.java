package com.github.rodrigohenriques.picnic.presenter;

import com.github.rodrigohenriques.picnic.view.CartListView;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

public interface CartListPresenter extends Presenter {
    void attachView(CartListView view);
    void clickedAt(ProductViewModel product, int position);
}
