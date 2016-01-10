package com.github.rodrigohenriques.picnic.viewmodel;

import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.picnic.ui.custom.CurrencyFormatter;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel
public class ProductViewModel {
    @ParcelProperty("description") private final String description;
    @ParcelProperty("formattedPrice") private final String formattedPrice;
    @ParcelProperty("name") private final String name;
    @ParcelProperty("imageUrl") private final String imageUrl;

    @ParcelConstructor
    public ProductViewModel(@ParcelProperty("description") String description,
                            @ParcelProperty("formattedPrice") String formattedPrice,
                            @ParcelProperty("name") String name,
                            @ParcelProperty("imageUrl") String imageUrl) {
        this.description = description;
        this.formattedPrice = formattedPrice;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public ProductViewModel(Product product, CurrencyFormatter currencyFormatter) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.formattedPrice = currencyFormatter.format(product.getPrice());
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
