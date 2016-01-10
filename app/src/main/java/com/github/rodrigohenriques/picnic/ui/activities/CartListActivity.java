package com.github.rodrigohenriques.picnic.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.github.rodrigohenriques.picnic.R;
import com.github.rodrigohenriques.picnic.presenter.CartListPresenter;
import com.github.rodrigohenriques.picnic.ui.adapter.CartListAdapter;
import com.github.rodrigohenriques.picnic.view.CartListView;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

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
        return R.layout.activity_products;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
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
    public void onClick(ProductViewModel product, int position) {
        Snackbar.make(mRecyclerView, product.getName(), Snackbar.LENGTH_LONG).show();
    }
}
