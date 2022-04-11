package com.factory.demofactory.sale.interact;

import android.content.Context;

import com.factory.demofactory.sale.presenter.PrintSalePresenter;
import com.factory.demofactory.sale.repository.PrintSaleRepository;
import com.factory.demofactory.sale.repository.PrintSaleRepositoryImpl;

public class PrintSaleInteractImpl implements PrintSaleInteract{

    private PrintSalePresenter presenter;
    private PrintSaleRepository repository;

    public PrintSaleInteractImpl(Context context, PrintSalePresenter presenter){
        this.presenter = presenter;
        repository = new PrintSaleRepositoryImpl(context, presenter);
    }

    @Override
    public void informationSale(int saleId) {
        repository.informationSale(saleId);
    }
}
