package com.factory.demofactory.transmitter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.Transmitter;
import com.factory.demofactory.R;
import com.factory.demofactory.transmitter.OnSelectItemListener;

import java.util.List;

public class TransmitterAdapter extends RecyclerView.Adapter<TransmitterAdapter.ViewHolder>{

    private Context context;
    private List<Transmitter> transmitters;
    private OnSelectItemListener listener;

    public void setListener(OnSelectItemListener listener){
        this.listener = listener;
    }

    public TransmitterAdapter(Context context, List<Transmitter> transmitters){
        this.context = context;
        this.transmitters = transmitters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_list, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transmitter transmitter = transmitters.get(position);
        holder.businessName.setText(transmitter.businessName);
        holder.rif.setText(transmitter.rif);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectItemListener(transmitter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transmitters.size();
    }

    @Override
    public long getItemId(int position) {
        return transmitters.get(position).id;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView businessName;
        public TextView rif;
        public LinearLayout content;

        public ViewHolder(View view) {
            super(view);
            businessName = (TextView) view.findViewById(R.id.name);
            rif = (TextView) view.findViewById(R.id.identification);
            content = (LinearLayout) view.findViewById(R.id.content_item);
        }
    }
}
