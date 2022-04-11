package com.factory.demofactory.sale.interact;

import android.content.Context;

import com.factory.database.entity.ProducView;
import com.factory.demofactory.sale.presenter.CreateSalePresenter;
import com.factory.demofactory.sale.presenter.CreateSalePresenterImpl;
import com.factory.demofactory.sale.repository.CreateSaleRepository;
import com.factory.demofactory.sale.repository.CreateSaleRepositoryImpl;

import java.util.List;

public class CreateSaleInteractImpl implements CreateSaleInteract {

    private CreateSalePresenter presenter;
    private CreateSaleRepository repository;

    public CreateSaleInteractImpl(Context context, CreateSalePresenterImpl presenter) {
        this.presenter = presenter;
        this.repository = new CreateSaleRepositoryImpl(context, presenter);
    }

    @Override
    public void createSale(List<ProducView> productViews, int clientId, int transmitterId) {
        repository.createSale(productViews, clientId, transmitterId);
    }

    @Override
    public double calculateTotal(List<ProducView> productViews) {
        return repository.calculateTotal(productViews);
    }
}
