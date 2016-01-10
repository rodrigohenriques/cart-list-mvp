package com.github.rodrigohenriques.picnic.ui.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.github.rodrigohenriques.picnic.R;
import com.github.rodrigohenriques.picnic.ui.adapter.ProductDetailAdapter;
import com.github.rodrigohenriques.picnic.view.ProductDetailView;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;

public class ProductDetailActivity extends BaseActivity implements ProductDetailView {
    public static final String PRODUCT = "product";

    private ProductViewModel mProduct;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.imageview_product_detail_cover) ImageView mImageViewAlbumCover;
    @Bind(R.id.recycler) RecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplicationComponent.inject(this);

        mProduct = Parcels.unwrap(getIntent().getParcelableExtra(PRODUCT));

        configureViews();

        showData();
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this)
                .load(mProduct.getImageUrl())
                .fit()
                .placeholder(R.drawable.placeholder)
                .into(mImageViewAlbumCover);
    }

    private void showData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ProductDetailAdapter(mProduct.getName(), mProduct.getFormattedPrice(), mProduct.getDescription()));
    }
}
