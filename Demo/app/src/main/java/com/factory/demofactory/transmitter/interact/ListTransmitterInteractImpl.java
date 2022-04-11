package com.factory.demofactory.transmitter.interact;

import android.content.Context;

import com.factory.demofactory.transmitter.presenter.ListTransmitterPresenter;
import com.factory.demofactory.transmitter.repository.ListTransmitterRepository;
import com.factory.demofactory.transmitter.repository.ListTransmitterRepositoryImpl;

public class ListTransmitterInteractImpl implements ListTransmitterInteract {

    private ListTransmitterPresenter presenter;
    private ListTransmitterRepository repository;

    public ListTransmitterInteractImpl(Context context, ListTransmitterPresenter presenter){
        this.presenter = presenter;
        this.repository = new ListTransmitterRepositoryImpl(context, presenter);
    }

    @Override
    public void getTransmitter() {
        this.repository.getTransmitter();
    }
}
