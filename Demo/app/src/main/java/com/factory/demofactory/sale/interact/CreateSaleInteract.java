package com.factory.demofactory.sale.interact;

import com.factory.database.entity.ProducView;

import java.util.List;

public interface CreateSaleInteract {
    void createSale(List<ProducView> productViews, int clientId, int transmitterId);
    double calculateTotal(List<ProducView> productViews);
}
