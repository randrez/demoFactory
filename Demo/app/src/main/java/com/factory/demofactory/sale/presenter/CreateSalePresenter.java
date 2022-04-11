package com.factory.demofactory.sale.presenter;

import com.factory.database.entity.ProducView;

import java.util.List;

public interface CreateSalePresenter {
    double calculateTotal(List<ProducView> productViews);
    void createSale(List<ProducView> productViews, int clientId, int transmitterId);
    void success(int saleId);
    void error(String tittle, String message);
}
