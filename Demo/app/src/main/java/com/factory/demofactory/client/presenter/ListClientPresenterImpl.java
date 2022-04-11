package com.factory.demofactory.client.presenter;

import android.content.Context;

import com.factory.database.entity.Client;
import com.factory.demofactory.client.interact.ListClientInteract;
import com.factory.demofactory.client.interact.ListClientInteractImpl;
import com.factory.demofactory.client.view.ListClientView;

import java.util.List;
import java.util.Map;

public class ListClientPresenterImpl implements ListClientPresenter {

    private ListClientView view;
    private ListClientInteract interact;

    public ListClientPresenterImpl(Context context, ListClientView view){
        this.view = view;
        this.interact = new ListClientInteractImpl(context, this);
    }

    @Override
    public void getClients() {
        view.showProgressDialog();
        this.interact.getClients();
    }

    @Override
    public void successClients(List<Client> clients) {
        view.hideProgressDialog();
        view.getListClients(clients);
    }

    @Override
    public void errorClients(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
