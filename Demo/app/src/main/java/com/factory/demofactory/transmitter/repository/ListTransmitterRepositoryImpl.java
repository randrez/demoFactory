package com.factory.demofactory.transmitter.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Transmitter;
import com.factory.demofactory.R;
import com.factory.demofactory.transmitter.presenter.ListTransmitterPresenter;

import java.util.List;

public class ListTransmitterRepositoryImpl implements ListTransmitterRepository{

    private ListTransmitterPresenter presenter;
    private Context context;
    private AppDataBase appDataBase;

    public ListTransmitterRepositoryImpl(Context context, ListTransmitterPresenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void getTransmitter() {
        List<Transmitter> transmitters = appDataBase.transmitterDao().getAll();
        if(transmitters.size() > 0){
            presenter.success(transmitters);
        }else{
            presenter.error(context.getString(R.string.load_list_transmitter), context.getString(R.string.empty_list_or_error_transmitter));
        }
    }
}
