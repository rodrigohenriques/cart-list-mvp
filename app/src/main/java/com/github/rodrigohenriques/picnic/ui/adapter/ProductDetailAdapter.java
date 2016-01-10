package com.github.rodrigohenriques.picnic.ui.adapter;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rodrigohenriques.picnic.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ViewHolder> {
    private final List<Pair<String, String>> mValues;

    public ProductDetailAdapter(String name, String price, String description) {
        this.mValues = new ArrayList<>();
        this.mValues.add(new Pair<>("Name:", name));
        this.mValues.add(new Pair<>("Price:", price));
        this.mValues.add(new Pair<>("Description:", description));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_holder_product_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Pair<String, String> pair = mValues.get(position);

        holder.label.setText(pair.first);
        holder.value.setText(pair.second);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.label) TextView label;
        @Bind(R.id.value) TextView value;

        View rootView;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);

            this.rootView = rootView;
        }
    }
}
