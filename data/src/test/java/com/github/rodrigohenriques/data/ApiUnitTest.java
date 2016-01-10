package com.github.rodrigohenriques.data;

import com.github.rodrigohenriques.data.api.PicNicApi;
import com.github.rodrigohenriques.data.entities.CartListResponse;
import com.github.rodrigohenriques.data.entities.ProductResponse;
import com.github.rodrigohenriques.data.internal.di.DataModule;

import org.junit.Test;

import retrofit.Call;
import retrofit.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ApiUnitTest {
    @Test
    public void testListProducts() throws Exception {
        PicNicApi api = new DataModule().provideApi();

        Call<CartListResponse> call = api.getCartList();

        Response<CartListResponse> response = call.execute();

        assertTrue(response.isSuccess());

        CartListResponse cartListResponse = response.body();

        assertNotNull(cartListResponse);

        assertThat(cartListResponse.products.size(), is(12));
    }

    @Test
    public void testGetAlbumDetailedById() throws Exception {
        PicNicApi api = new DataModule().provideApi();

        Call<ProductResponse> call = api.getProductDetailByProductId(1);

        Response<ProductResponse> response = call.execute();

        assertTrue(response.isSuccess());

        ProductResponse product = response.body();

        assertNotNull(product);

        assertThat(product.price, is(120L));
    }
}