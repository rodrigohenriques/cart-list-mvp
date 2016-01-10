package com.github.rodrigohenriques.picnic.viewmodel;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.picnic.ui.custom.CurrencyFormatter;

public class ProductViewModel {
    private String formattedPrice;
    private Product product;

    public ProductViewModel(Product product, CurrencyFormatter currencyFormatter) {
        this.product = product;
        this.formattedPrice = currencyFormatter.format(product.price);
    }

    public String getName() {
        return product.name;
    }

    public String getPrice() {
        return formattedPrice;
    }

    public String getImageUrl() {
        return product.imageUrl;
    }
}
