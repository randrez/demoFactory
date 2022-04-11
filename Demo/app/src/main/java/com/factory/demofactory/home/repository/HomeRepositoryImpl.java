package com.factory.demofactory.home.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.demofactory.home.presenter.HomePresenter;

public class HomeRepositoryImpl implements HomeRepository {

    private Context context;
    private HomePresenter presenter;
    private AppDataBase appDataBase;

    public HomeRepositoryImpl(Context context, HomePresenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void hasListTransmitter() {
       boolean hasTransmitter =  this.appDataBase.transmitterDao().hasTransmitter();
       presenter.resultHasTransmitter(hasTransmitter);
    }

    @Override
    public void hasListClients() {
        boolean hasClients = this.appDataBase.clientDao().hasClient();
        presenter.resultHasListClients(hasClients);
    }

    @Override
    public void hasListSales() {
        boolean hasSales = this.appDataBase.saleDao().hasSale();
        presenter.resultHasListSales(hasSales);
    }
}
