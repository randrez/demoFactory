package com.factory.demofactory.transmitter.presenter;

import android.content.Context;

import com.factory.demofactory.transmitter.interact.TransmitterInteract;
import com.factory.demofactory.transmitter.interact.TransmitterInteractImpl;
import com.factory.demofactory.transmitter.view.TransmitterView;

import java.util.Map;

public class TransmitterPresenterImpl implements TransmitterPresenter{

    private final TransmitterView view;
    private final TransmitterInteract interact;

    public TransmitterPresenterImpl(Context context, TransmitterView view){
        this.view = view;
        this.interact = new TransmitterInteractImpl(context, this);
    }

    @Override
    public void insertTransmitter(Map<String, String> transmitterData) {
        view.showProgressDialog();
        interact.insertTransmitter(transmitterData);
    }

    @Override
    public void success(int transmitterId) {
        view.hideProgressDialog();
        view.goCreateSale(transmitterId);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
