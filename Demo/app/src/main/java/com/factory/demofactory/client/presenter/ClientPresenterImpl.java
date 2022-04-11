package com.factory.demofactory.client.presenter;

import android.content.Context;

import com.factory.demofactory.client.interact.ClientInteract;
import com.factory.demofactory.client.interact.ClientInteractImpl;
import com.factory.demofactory.client.view.ClientView;

import java.util.Map;

public class ClientPresenterImpl implements ClientPresenter {

    private ClientInteract interact;
    private ClientView view;

    public ClientPresenterImpl(Context context, ClientView view){
        this.view = view;
        this.interact = new ClientInteractImpl(context, this);
    }

    @Override
    public void createClient(Map<String, String> dataClient) {
        view.showProgressDialog();
        interact.createClient(dataClient);
    }

    @Override
    public void success(int id) {
        view.hideProgressDialog();
        view.goCreateTransmitter(id);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
