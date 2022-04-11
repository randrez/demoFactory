package com.factory.demofactory.client.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Client;
import com.factory.demofactory.R;
import com.factory.demofactory.client.presenter.ListClientPresenter;

import java.util.List;

public class ListClientRepositoryImpl implements ListClientRepository{

    private ListClientPresenter presenter;
    private Context context;
    private AppDataBase appDataBase;

    public ListClientRepositoryImpl(Context context, ListClientPresenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void getClients() {
        List<Client> clientList = appDataBase.clientDao().getAll();
        if(clientList.size() > 0){
            presenter.successClients(clientList);
        }else{
            presenter.errorClients(context.getString(R.string.load_list_client), context.getString(R.string.empty_list_or_error_client));
        }
    }
}
