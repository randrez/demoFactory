package com.factory.demofactory.transmitter.presenter;

import java.util.Map;

public interface TransmitterPresenter {
    void insertTransmitter(Map<String, String> transmitterData);
    void success(int transmitterId);
    void error(String tittle, String message);
}
