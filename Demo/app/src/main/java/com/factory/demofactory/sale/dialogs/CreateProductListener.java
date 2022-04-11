package com.factory.demofactory.sale.dialogs;

import com.factory.database.entity.ProducView;

public interface CreateProductListener {
    void addProduct(ProducView productList);
    void showMessageCreateProductToast(String message);
}
