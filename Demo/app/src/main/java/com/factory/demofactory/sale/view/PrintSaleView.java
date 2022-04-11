package com.factory.demofactory.sale.view;

import com.factory.database.entity.ProducView;

import java.util.List;
import java.util.Map;

public interface PrintSaleView {
    void getSale(Map<String, String> dataSale, List<ProducView> products);
    void drawList(List<ProducView> products);
    void showProgressDialog();
    void hideProgressDialog();
    void message(String tittle, String message);
}
