package com.factory.demofactory.client.interact;

import android.content.Context;

import com.factory.demofactory.client.presenter.ClientPresenter;
import com.factory.demofactory.client.repository.ClientRepository;
import com.factory.demofactory.client.repository.ClientRepositoryImpl;

import java.util.Map;

public class ClientInteractImpl implements ClientInteract{

    private ClientPresenter presenter;
    private ClientRepository repository;

    public ClientInteractImpl(Context context, ClientPresenter presenter){
        this.presenter = presenter;
        this.repository = new ClientRepositoryImpl(context, presenter);
    }

    @Override
    public void createClient(Map<String, String> dataClient) {
        repository.createClient(dataClient);
    }
}
