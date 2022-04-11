package com.factory.demofactory.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.demofactory.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder>{

    private Context context;
    private List<JSONObject> objects;
    private OnSelectedItemSale listener;

    public SaleAdapter(Context context, List<JSONObject> objects, OnSelectedItemSale listener){
        this.context = context;
        this.objects = objects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_sale, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.ViewHolder holder, int position) {
        try{
            JSONObject object = objects.get(position);
            holder.business.setText(object.getString("business"));
            holder.client.setText(object.getString("client"));
            holder.total.setText("$"+object.getString("total"));
            holder.cardSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        listener.selectItemSale(object.getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public long getItemId(int position) {
        try{
            JSONObject object = objects.get(position);
            return (long) object.getInt("id");
        }catch (Exception e){
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView business;
        public TextView client;
        public TextView total;
        public CardView cardSale;

        public ViewHolder(@NonNull View view) {
            super(view);
            business = (TextView) view.findViewById(R.id.business);
            client = (TextView) view.findViewById(R.id.client);
            total = (TextView) view.findViewById(R.id.total);
            cardSale = (CardView) view.findViewById(R.id.card_sale);
        }
    }
}
