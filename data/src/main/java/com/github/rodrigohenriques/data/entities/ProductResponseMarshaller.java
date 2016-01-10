package com.github.rodrigohenriques.data.entities;

import com.github.rodrigohenriques.domain.entities.Product;

public class ProductResponseMarshaller implements Marshaller<ProductResponse, Product> {
    @Override
    public Product marshal(ProductResponse productResponse) {
        return new Product(productResponse.identifier,
                productResponse.name,
                productResponse.imageUrl,
                productResponse.description,
                productResponse.price);
    }
}
