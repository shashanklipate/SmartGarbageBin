package com.example.nitesh.smartgarbagetracksystem;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nitesh on 12-Apr-18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    Context mCtx;
    List<Product> productList;


    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_layout,
                parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewname.setText(product.getComplainter_Name());
        holder.textViewSlocation.setText(product.getLocation());
        holder.textviewremark.setText(String.valueOf(product.getRemark()));
       // holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage(), null));


    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewname, textViewSlocation, textviewremark;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewname = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewSlocation = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            textviewremark = (TextView) itemView.findViewById(R.id.textViewPrice);
        }
    }



}
