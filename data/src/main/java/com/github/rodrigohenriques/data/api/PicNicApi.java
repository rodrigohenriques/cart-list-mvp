package com.github.rodrigohenriques.data.api;

import com.github.rodrigohenriques.data.entities.CartListResponse;
import com.github.rodrigohenriques.data.entities.ProductResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PicNicApi {
    @GET("cart/list")
    Call<CartListResponse> getCartList();

    @GET("cart/{productId}/detail")
    Call<ProductResponse> getProductDetailByProductId(@Path("productId") String productId);
}
