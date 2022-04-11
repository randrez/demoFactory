package com.factory.demofactory.transmitter.interact;

import android.content.Context;

import com.factory.demofactory.transmitter.presenter.TransmitterPresenter;
import com.factory.demofactory.transmitter.repository.TransmitterRepository;
import com.factory.demofactory.transmitter.repository.TransmitterRepositoryImpl;

import java.util.Map;

public class TransmitterInteractImpl implements TransmitterInteract {

    private TransmitterPresenter presenter;
    private TransmitterRepository repository;

    public TransmitterInteractImpl(Context context, TransmitterPresenter presenter){
        this.presenter = presenter;
        this.repository = new TransmitterRepositoryImpl(context, presenter);
    }

    @Override
    public void insertTransmitter(Map<String, String> transmitterData) {
        this.repository.insertTransmitter(transmitterData);
    }
}
