package com.factory.demofactory.sale.presenter;

import android.content.Context;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.sale.interact.PrintSaleInteract;
import com.factory.demofactory.sale.interact.PrintSaleInteractImpl;
import com.factory.demofactory.sale.view.PrintSaleView;

import java.util.List;
import java.util.Map;

public class PrintSalePresenterImpl implements PrintSalePresenter{

    private PrintSaleView view;
    private PrintSaleInteract interact;

    public PrintSalePresenterImpl(Context context, PrintSaleView view){
        this.view = view;
        this.interact = new PrintSaleInteractImpl(context, this);
    }

    @Override
    public void informationSale(int saleId) {
        view.showProgressDialog();
        interact.informationSale(saleId);
    }

    @Override
    public void success(Map<String, String> dataSale, List<ProducView> products) {
        view.hideProgressDialog();
        view.getSale(dataSale, products);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.message(tittle, message);
    }
}
