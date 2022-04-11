package com.factory.demofactory.client.view;

public interface ClientView {
    void showMessage(String tittle, String message);
    void showProgressDialog();
    void hideProgressDialog();
    void goCreateTransmitter(int clientId);
}
