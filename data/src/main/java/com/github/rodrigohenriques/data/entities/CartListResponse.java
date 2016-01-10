package com.github.rodrigohenriques.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CartListResponse {
    @JsonProperty("products") public List<ProductResponse> products;
}
