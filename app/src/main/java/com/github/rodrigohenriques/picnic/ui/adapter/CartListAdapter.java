package com.github.rodrigohenriques.picnic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rodrigohenriques.picnic.R;
import com.github.rodrigohenriques.picnic.viewmodel.ProductViewModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    Context mContext;
    List<ProductViewModel> mProducts;
    OnItemClickListener mItemClickListener;

    public CartListAdapter(Context context, List<ProductViewModel> mProducts) {
        this.mContext = context;
        this.mProducts = mProducts;
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_holder_cart_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProductViewModel product = mProducts.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());

//        Picasso.with(mContext)
//                .load(album.getCoverUrl())
//                .fit()
//                .transform(PaletteTransformation.instance())
//                .placeholder(R.drawable.placeholder)
//                .into(holder.albumCoverImage, new Callback.EmptyCallback() {
//                    @Override
//                    public void onSuccess() {
//                        Bitmap bitmap = ((BitmapDrawable) holder.albumCoverImage.getDrawable()).getBitmap(); // Ew!
//
//                        Palette palette = PaletteTransformation.getPalette(bitmap);
//
//                        Palette.Swatch swatch = palette.getDarkVibrantSwatch();
//
//                        if (swatch != null) {
//
//                            GradientDrawable gd = new GradientDrawable(
//                                    GradientDrawable.Orientation.BOTTOM_TOP,
//                                    new int[] { swatch.getRgb(), Color.alpha(swatch.getRgb()) });
//                            gd.setCornerRadius(0f);
//
//                            holder.albumGroupInfo.setBackground(gd);
//                        }
//                    }
//                });

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onClick(product, position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_product) ImageView productImage;
        @Bind(R.id.textview_product_name) TextView productName;
        @Bind(R.id.textview_product_price) TextView productPrice;
        @Bind(R.id.viewgroup_product_info) ViewGroup productGroupInfo;

        View rootView;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);

            this.rootView = rootView;
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(ProductViewModel product, int position);
    }
}
