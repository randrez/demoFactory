package com.factory.demofactory.sale.view;

import org.json.JSONObject;

import java.util.List;

public interface ListSalesView {
    void showMessage(String tittle, String message);
    void showProgressDialog();
    void hideProgressDialog();
    void getListSale(List<JSONObject> sales);
}
