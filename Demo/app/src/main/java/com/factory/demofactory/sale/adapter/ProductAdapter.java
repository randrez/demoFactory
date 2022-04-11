package com.factory.demofactory.sale.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.R;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final Context context;
    private final List<ProducView> products;
    private OnChangeQuantityListener listener;

    public void setListener(OnChangeQuantityListener listener) {
        this.listener = listener;
    }

    public ProductAdapter(Context context, List<ProducView> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_product, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProducView product = products.get(position);
        holder.description.setText(product.description);
        holder.price.setText("$" + (int)product.price);
        if(product.quantity > 0){
            holder.edtQuantity.setText(String.valueOf(product.quantity));
        }
        holder.edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if (holder.edtQuantity != null && !s.toString().isEmpty()) {
                        int quantity = Integer.parseInt(s.toString());
                        if (quantity > 0 && quantity < 100) {
                            product.quantity = quantity;
                        }else{
                            holder.edtQuantity.setText(null);
                            product.quantity = 0;
                        }
                        listener.updateProductsEdt();
                    }else if(s.toString().isEmpty()){
                        product.quantity = 0;
                        listener.updateProductsEdt();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        holder.btnPlus.setOnClickListener(v -> {
            if (!holder.edtQuantity.toString().isEmpty()) {
                int sum = product.quantity + 1;
                if (sum > 0 && sum < 100) {
                    product.quantity = sum;
                    holder.edtQuantity.setText(String.valueOf(product.quantity));
                    listener.updateProductsEdt();
                }
            }
        });
        holder.btnMinus.setOnClickListener(v -> {
            if (!holder.edtQuantity.toString().isEmpty()) {
                if (product.quantity > 0) {
                    int rest = product.quantity - 1;
                    if(rest > 0){
                        product.quantity = rest;
                        holder.edtQuantity.setText(String.valueOf(product.quantity));
                    }else{
                        product.quantity = 0;
                        holder.edtQuantity.setText(null);
                    }
                }else{
                    product.quantity = 0;
                    holder.edtQuantity.setText(null);
                }
                listener.updateProductsEdt();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return (long) products.get(position).id;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView description;
        public TextView price;
        public Button btnPlus;
        public EditText edtQuantity;
        public Button btnMinus;
        public LinearLayout remove;

        public ViewHolder(@NonNull View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.product_description);
            price = (TextView) view.findViewById(R.id.price_product);
            btnPlus = (Button) view.findViewById(R.id.btn_plus);
            edtQuantity = (EditText) view.findViewById(R.id.edt_quantity);
            btnMinus = (Button) view.findViewById(R.id.btn_minus);
            remove = (LinearLayout) view.findViewById(R.id.remove);
        }
    }
}
