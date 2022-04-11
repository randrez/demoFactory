package com.factory.demofactory.home.presenter;

import android.content.Context;

import com.factory.demofactory.home.interact.HomeInteract;
import com.factory.demofactory.home.interact.HomeInteractImpl;
import com.factory.demofactory.home.view.HomeView;

public class HomePresenterImpl implements HomePresenter{

    private HomeView view;
    private HomeInteract interact;

    public HomePresenterImpl(Context context, HomeView view){
        this.view = view;
        this.interact = new HomeInteractImpl(context, this);
    }

    @Override
    public void hasListTransmitter() {
        interact.hasListTransmitter();
    }

    @Override
    public void hasListClients() {
        interact.hasListClients();
    }

    @Override
    public void hasListSales() {
        interact.hasListSales();
    }

    @Override
    public void resultHasTransmitter(boolean hasData) {
        if(hasData){
            view.showItemTransmitter();
        }else{
            view.hideItemTransmitter();
        }
    }

    @Override
    public void resultHasListClients(boolean hasData) {
        if(hasData){
            view.showItemClients();
        }else{
            view.hideItemClients();
        }
    }

    @Override
    public void resultHasListSales(boolean hasData) {
        if(hasData){
            view.showItemSales();
        }else{
            view.hideItemSales();
        }
    }
}
