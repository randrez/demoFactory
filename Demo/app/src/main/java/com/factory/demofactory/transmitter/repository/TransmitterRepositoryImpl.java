package com.factory.demofactory.transmitter.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Transmitter;
import com.factory.demofactory.R;
import com.factory.demofactory.transmitter.presenter.TransmitterPresenter;

import java.util.Map;

public class TransmitterRepositoryImpl implements TransmitterRepository{

    private TransmitterPresenter presenter;
    private Context context;
    private AppDataBase appDataBase;

    public TransmitterRepositoryImpl(Context context, TransmitterPresenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void insertTransmitter(Map<String, String> transmitterData) {
        Transmitter transmitter = new Transmitter();
        transmitter.businessName = transmitterData.get("businessName");
        transmitter.rif = transmitterData.get("rif");
        int id = (int) appDataBase.transmitterDao().insertTransmitter(transmitter);
        if(id > 0){
            this.presenter.success(id);
        }else{
            this.presenter.error(context.getString(R.string.tittle_transmitter), context.getString(R.string.error_insert_transmitter));
        }
    }
}
