package com.factory.demofactory.client.interact;

import android.content.Context;

import com.factory.demofactory.client.presenter.ListClientPresenter;
import com.factory.demofactory.client.repository.ListClientRepository;
import com.factory.demofactory.client.repository.ListClientRepositoryImpl;

public class ListClientInteractImpl implements ListClientInteract{

    private ListClientPresenter presenter;
    private ListClientRepository repository;

    public ListClientInteractImpl(Context context, ListClientPresenter presenter){
        this.presenter = presenter;
        this.repository = new ListClientRepositoryImpl(context, presenter);
    }

    @Override
    public void getClients() {
        this.repository.getClients();
    }
}
