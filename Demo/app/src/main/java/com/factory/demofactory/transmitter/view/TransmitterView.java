package com.factory.demofactory.transmitter.view;

public interface TransmitterView {
    void showMessage(String tittle, String message);
    void showProgressDialog();
    void hideProgressDialog();
    void goCreateSale(int transmitterId);
}
