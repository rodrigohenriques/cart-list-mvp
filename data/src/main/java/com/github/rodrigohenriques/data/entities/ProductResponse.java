package com.github.rodrigohenriques.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {
    @JsonProperty("product_id") public String identifier;
    @JsonProperty("name") public String name;
    @JsonProperty("image") public String imageUrl;
    @JsonProperty("description") public String description;
    @JsonProperty("price") public long price;
}
