package com.factory.demofactory.sale.repository;

import com.factory.database.entity.ProducView;

import java.util.List;

public interface CreateSaleRepository {
    void createSale(List<ProducView> productViews, int clientId, int transmitterId);
    double calculateTotal(List<ProducView> productViews);
}
