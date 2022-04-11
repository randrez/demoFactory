package com.factory.demofactory.home.interact;

import android.content.Context;

import com.factory.demofactory.home.presenter.HomePresenter;
import com.factory.demofactory.home.repository.HomeRepository;
import com.factory.demofactory.home.repository.HomeRepositoryImpl;

public class HomeInteractImpl implements HomeInteract {

    private HomePresenter presenter;
    private HomeRepository repository;

    public HomeInteractImpl(Context context, HomePresenter presenter){
        this.presenter = presenter;
        this.repository = new HomeRepositoryImpl(context, presenter);
    }

    @Override
    public void hasListTransmitter() {
        repository.hasListTransmitter();
    }

    @Override
    public void hasListClients() {
        repository.hasListClients();
    }

    @Override
    public void hasListSales() {
        repository.hasListSales();
    }
}
