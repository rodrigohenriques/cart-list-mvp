package com.github.rodrigohenriques.picnic.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.github.rodrigohenriques.picnic.R;
import com.github.rodrigohenriques.picnic.presenter.CartListPresenter;
import com.github.rodrigohenriques.picnic.ui.adapter.CartListAdapter;
import com.github.rodrigohenriques.picnic.view.CartListView;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class CartListActivity extends BaseActivity implements CartListView, CartListAdapter.OnItemClickListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler) RecyclerView mRecyclerView;

    @Inject CartListPresenter mCartListPresenter;

    public static final int SPAN_COUNT = 2;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cart_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);

        mApplicationComponent.inject(this);

        mCartListPresenter.attachView(this);
    }

    @Override
    public void showData(List<ProductViewModel> products) {
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        CartListAdapter adapter = new CartListAdapter(this, products);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void openProductDetail(ProductViewModel productViewModel) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT, Parcels.wrap(productViewModel));
        startActivity(intent);
    }

    @Override
    public void onClick(ProductViewModel product, int position) {
        mCartListPresenter.clickedAt(product, position);
    }
}
