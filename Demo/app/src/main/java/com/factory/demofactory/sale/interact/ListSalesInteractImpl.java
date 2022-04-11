package com.factory.demofactory.sale.interact;

import android.content.Context;

import com.factory.demofactory.sale.presenter.ListSalesPresenter;
import com.factory.demofactory.sale.repository.ListSalesRepository;
import com.factory.demofactory.sale.repository.ListSalesRepositoryImpl;

public class ListSalesInteractImpl implements ListSalesInteract {

    private ListSalesRepository repository;
    private ListSalesPresenter presenter;

    public ListSalesInteractImpl(Context context, ListSalesPresenter presenter){
        this.presenter = presenter;
        this.repository = new ListSalesRepositoryImpl(context, presenter);
    }

    @Override
    public void getSales() {
        repository.getSales();
    }
}
