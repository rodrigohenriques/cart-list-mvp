package com.github.rodrigohenriques.picnic.view;

import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

import java.util.List;

public interface CartListView extends IView {
    void showData(List<ProductViewModel> products);
    void openProductDetail(String identifier);
}
