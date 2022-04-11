package com.factory.demofactory.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.factory.database.entity.Client;
import com.factory.demofactory.R;
import com.factory.demofactory.client.OnSelectItemListener;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private Context context;
    private List<Client> clients;
    private OnSelectItemListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView clientName;
        public TextView clientIdentification;
        public LinearLayout content;

        public ViewHolder(View view) {
            super(view);
            clientName = (TextView) view.findViewById(R.id.name);
            clientIdentification = (TextView) view.findViewById(R.id.identification);
            content = (LinearLayout) view.findViewById(R.id.content_item);
        }
    }

    public void setListener(OnSelectItemListener listener){
        this.listener = listener;
    }

    public ClientAdapter(Context context, List<Client> clients) {
        this.context = context;
        this.clients = clients;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.clientName.setText(client.firstName + " " + client.lastName);
        holder.clientIdentification.setText(client.identification);
        holder.content.setOnClickListener(v -> listener.selectItemListener(client));
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    @Override
    public long getItemId(int position) {
        return clients.get(position).id;
    }


}
