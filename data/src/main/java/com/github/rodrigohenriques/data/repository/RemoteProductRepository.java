package com.github.rodrigohenriques.data.repository;

import com.github.rodrigohenriques.data.api.PicNicApi;
import com.github.rodrigohenriques.data.entities.CartListResponse;
import com.github.rodrigohenriques.data.entities.ListMarshaller;
import com.github.rodrigohenriques.data.entities.ProductResponse;
import com.github.rodrigohenriques.data.entities.ProductResponseMarshaller;
import com.github.rodrigohenriques.domain.entities.Product;
import com.github.rodrigohenriques.domain.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;

public class RemoteProductRepository implements ProductRepository {

    PicNicApi mPicNicApi;

    @Inject
    public RemoteProductRepository(PicNicApi mPicNicApi) {
        this.mPicNicApi = mPicNicApi;
    }

    @Override
    public List<Product> getCartList() throws Exception {
        Call<CartListResponse> call = mPicNicApi.getCartList();

        Response<CartListResponse> response;
        try {
            response = call.execute();

            if (response.isSuccess()) {
                CartListResponse cartListResponse = response.body();

                ListMarshaller<ProductResponse, Product> listMarshaller = new ListMarshaller<>(new ProductResponseMarshaller() {
                    @Override
                    public Product marshal(ProductResponse productResponse) {
                        try {
                            Product productDetail = getProductDetail(productResponse.identifier);
                            return productDetail;
                        } catch (IOException e) {
                            // TODO: should review this strategy before being productive
                            return super.marshal(productResponse);
                        }
                    }
                });

                return listMarshaller.marshal(cartListResponse.products);
            } else {
                throw new IOException("should implement this scenario correctly: " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("should implement this scenario correctly", e);
        }
    }

    @Override
    public Product getProductDetail(String productId) throws IOException {
        Call<ProductResponse> call = mPicNicApi.getProductDetailByProductId(productId);

        Response<ProductResponse> response;
        try {
            response = call.execute();

            if (response.isSuccess()) {
                ProductResponse productResponse = response.body();
                ProductResponseMarshaller marshaller = new ProductResponseMarshaller();
                return marshaller.marshal(productResponse);
            } else {
                throw new IOException("should implement this scenario correctly: " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("should implement this scenario correctly", e);
        }
    }
}
