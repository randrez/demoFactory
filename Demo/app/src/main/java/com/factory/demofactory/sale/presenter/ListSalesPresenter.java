package com.factory.demofactory.sale.presenter;

import org.json.JSONObject;

import java.util.List;

public interface ListSalesPresenter {
    void getSales();
    void success(List<JSONObject> sales);
    void error(String tittle, String message);
}
