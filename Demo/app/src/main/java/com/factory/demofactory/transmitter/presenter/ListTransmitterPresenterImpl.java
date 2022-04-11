package com.factory.demofactory.transmitter.presenter;

import android.content.Context;

import com.factory.database.entity.Transmitter;
import com.factory.demofactory.transmitter.interact.ListTransmitterInteract;
import com.factory.demofactory.transmitter.interact.ListTransmitterInteractImpl;
import com.factory.demofactory.transmitter.view.ListTransmitterView;

import java.util.List;

public class ListTransmitterPresenterImpl implements ListTransmitterPresenter{

    private ListTransmitterView view;
    private ListTransmitterInteract interact;

    public ListTransmitterPresenterImpl(Context context, ListTransmitterView view){
        this.view = view;
        this.interact = new ListTransmitterInteractImpl(context, this);
    }

    @Override
    public void getTransmitter() {
        view.showProgressDialog();
        this.interact.getTransmitter();
    }

    @Override
    public void success(List<Transmitter> transmitters) {
        view.hideProgressDialog();
        view.getListTransmitter(transmitters);
    }

    @Override
    public void error(String tittle, String message) {
        view.hideProgressDialog();
        view.showMessage(tittle, message);
    }
}
