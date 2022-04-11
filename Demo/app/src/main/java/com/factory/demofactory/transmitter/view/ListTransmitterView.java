package com.factory.demofactory.transmitter.view;

import com.factory.database.entity.Transmitter;

import java.util.List;

public interface ListTransmitterView {
    void showMessage(String tittle, String message);
    void showProgressDialog();
    void hideProgressDialog();
    void getListTransmitter(List<Transmitter> transmitters);
}
