package com.factory.demofactory.client;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.factory.database.entity.Client;
import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.client.adapter.ClientAdapter;
import com.factory.demofactory.client.presenter.ListClientPresenter;
import com.factory.demofactory.client.presenter.ListClientPresenterImpl;
import com.factory.demofactory.client.view.ListClientView;

import java.util.List;

public class ListClientFragment extends Fragment implements ListClientView, OnSelectItemListener {

    private ProgressDialog progressDialog;
    private RecyclerView listClient;

    public ListClientFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_client_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settingProgressDialog();

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).showActionBar();
            ((MainActivity) getActivity()).changeNameActionBar(R.string.list_clients);
        }

        ListClientPresenter presenter = new ListClientPresenterImpl(getActivity(), this);

        listClient = (RecyclerView) view.findViewById(R.id.list_client);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        listClient.setLayoutManager(layoutManager);

        presenter.getClients();
    }

    @Override
    public void showMessage(String tittle, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(tittle);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.accept), (dialog, id) -> dialog.dismiss()).create();
        builder.show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void getListClients(List<Client> clients) {
        ClientAdapter adapter = new ClientAdapter(getActivity(), clients);
        adapter.setListener(this);
        if(listClient != null){
            listClient.setAdapter(adapter);
        }
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.list_clients));
        progressDialog.setMessage(getString(R.string.load_clients));
    }

    @Override
    public void selectItemListener(Client client) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.create_sale));
        builder.setMessage(getString(R.string.you_wan_create_sale))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.accept), (dialog, id) -> {

                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .create();
        builder.show();
    }
}