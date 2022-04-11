package com.factory.demofactory.client.presenter;

import com.factory.database.entity.Client;

import java.util.List;

public interface ListClientPresenter {
    void getClients();
    void successClients(List<Client> clients);
    void errorClients(String tittle, String  message);
}
