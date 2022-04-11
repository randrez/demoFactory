package com.factory.demofactory.client.view;

import com.factory.database.entity.Client;

import java.util.List;

public interface ListClientView {
    void showMessage(String tittle, String message);
    void showProgressDialog();
    void hideProgressDialog();
    void getListClients(List<Client> clients);
}
