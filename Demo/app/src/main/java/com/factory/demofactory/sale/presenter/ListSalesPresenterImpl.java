package com.factory.demofactory.sale.presenter;

import android.content.Context;

import com.factory.demofactory.sale.interact.ListSalesInteract;
import com.factory.demofactory.sale.interact.ListSalesInteractImpl;
import com.factory.demofactory.sale.view.ListSalesView;

import org.json.JSONObject;

import java.util.List;

public class ListSalesPresenterImpl implements ListSalesPresenter{

    private ListSalesInteract interact;
    private ListSalesView view;

    public ListSalesPresenterImpl(Context context, ListSalesView view){
        this.view = view;
        this.interact = new ListSalesInteractImpl(context, this);
    }

    @Override
    public void getSales() {
        view.showProgressDialog();
        interact.getSales();
    }

    @Override
    public void success(List<JSONObject> sales) {
        view.hideProgressDialog();
        view.getListSale(sales);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
