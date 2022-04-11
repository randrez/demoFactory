package com.factory.demofactory.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProductSaleAdapter extends RecyclerView.Adapter<ProductSaleAdapter.ViewHolder>{

    private Context context;
    private List<ProducView> products;

    public ProductSaleAdapter(Context context, List<ProducView> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_product_print, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProducView product = products.get(position);
        holder.code.setText(product.code);
        holder.quantityPrice.setText(product.quantity+"X"+(int)product.price);
        holder.description.setText(product.description);
        int totalProductValue =  product.quantity *  (int) product.price;
        holder.totalProduct.setText("$"+totalProductValue);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).id;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView code;
        public TextView quantityPrice;
        public TextView description;
        public TextView totalProduct;

        public ViewHolder(@NonNull View view) {
            super(view);
            code = (TextView) view.findViewById(R.id.code_product);
            quantityPrice = (TextView) view.findViewById(R.id.quantity_price);
            description = (TextView) view.findViewById(R.id.description_product);
            totalProduct = (TextView) view.findViewById(R.id.total_product);
        }
    }
}
