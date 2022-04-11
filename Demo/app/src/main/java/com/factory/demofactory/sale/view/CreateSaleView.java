package com.factory.demofactory.sale.view;

import com.factory.database.entity.ProducView;

import java.util.List;

public interface CreateSaleView {
    void showProgressDialog();
    void hideProgressDialog();
    void drawProductList(List<ProducView> products);
    void calculateTotal(List<ProducView> products);
    void showMessage(String tittle, String message);
    void goPrintSale(int saleId);
    void showDialogProduct();
    void hideDialogProduct();
}
