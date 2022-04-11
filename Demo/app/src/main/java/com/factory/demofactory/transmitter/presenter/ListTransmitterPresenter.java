package com.factory.demofactory.transmitter.presenter;

import com.factory.database.entity.Transmitter;

import java.util.List;

public interface ListTransmitterPresenter {
    void getTransmitter();
    void success(List<Transmitter> transmitters);
    void error(String tittle, String  message);
}
