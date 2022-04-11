package com.factory.demofactory.sale.presenter;

import android.content.Context;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.sale.interact.CreateSaleInteract;
import com.factory.demofactory.sale.interact.CreateSaleInteractImpl;
import com.factory.demofactory.sale.view.CreateSaleView;

import java.util.List;

public class CreateSalePresenterImpl implements CreateSalePresenter{

    private CreateSaleView view;
    private CreateSaleInteract interact;

    public CreateSalePresenterImpl(Context context, CreateSaleView view){
        this.view = view;
        this.interact = new CreateSaleInteractImpl(context, this);
    }

    @Override
    public double calculateTotal(List<ProducView> productViews) {
        return interact.calculateTotal(productViews);
    }

    @Override
    public void createSale(List<ProducView> productViews, int clientId, int transmitterId) {
        view.showProgressDialog();
        interact.createSale(productViews, clientId, transmitterId);
    }

    @Override
    public void success(int saleId) {
        view.hideProgressDialog();
        view.goPrintSale(saleId);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
