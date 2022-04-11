package com.factory.demofactory.home.presenter;

import android.content.Context;

public interface HomePresenter {
    void hasListTransmitter();
    void hasListClients();
    void hasListSales();
    void resultHasTransmitter(boolean hasData);
    void resultHasListClients(boolean hasData);
    void resultHasListSales(boolean hasData);
}
