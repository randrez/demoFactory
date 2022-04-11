package com.factory.demofactory.sale.presenter;

import com.factory.database.entity.ProducView;

import java.util.List;
import java.util.Map;

public interface PrintSalePresenter {
    void informationSale(int saleId);
    void success(Map<String, String> dataSale, List<ProducView> products);
    void error(String tittle, String message);
}
