package com.factory.demofactory.transmitter;

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

import com.factory.database.entity.Transmitter;
import com.factory.demofactory.MainActivity;
import com.factory.demofactory.R;
import com.factory.demofactory.transmitter.adapter.TransmitterAdapter;
import com.factory.demofactory.transmitter.presenter.ListTransmitterPresenter;
import com.factory.demofactory.transmitter.presenter.ListTransmitterPresenterImpl;
import com.factory.demofactory.transmitter.view.ListTransmitterView;

import java.util.List;

public class ListTransmitterFragment extends Fragment implements ListTransmitterView, OnSelectItemListener {

    private ProgressDialog progressDialog;
    private RecyclerView listTransmitter;

    public ListTransmitterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_transmitter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settingProgressDialog();

        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).showActionBar();
            ((MainActivity) getActivity()).changeNameActionBar(R.string.list_transmitter);
        }

        ListTransmitterPresenter presenter = new ListTransmitterPresenterImpl(getActivity(), this);

        listTransmitter = (RecyclerView) view.findViewById(R.id.list_transmitter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listTransmitter.setLayoutManager(layoutManager);

        presenter.getTransmitter();
    }

    private void settingProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(getString(R.string.list_transmitter));
        progressDialog.setMessage(getString(R.string.load_clients));
    }

    @Override
    public void selectItemListener(Transmitter transmitter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.create_sale));
        builder.setMessage(getString(R.string.you_wan_create_sale_with_transmitter))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.accept), (dialog, id) -> {

                })
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .create();
        builder.show();
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
    public void getListTransmitter(List<Transmitter> transmitters) {
        TransmitterAdapter adapter = new TransmitterAdapter(getActivity(), transmitters);
        adapter.setListener(this);
        if(listTransmitter != null){
            listTransmitter.setAdapter(adapter);
        }
    }
}