package com.factory.demofactory.client.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Client;
import com.factory.demofactory.R;
import com.factory.demofactory.client.presenter.ClientPresenter;

import java.util.Map;

public class ClientRepositoryImpl implements ClientRepository {

    private ClientPresenter presenter;
    private Context context;
    private AppDataBase appDataBase;

    public ClientRepositoryImpl(Context context, ClientPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void createClient(Map<String, String> dataClient) {
        Client client = new Client();
        client.firstName = dataClient.get("firstName");
        client.lastName = dataClient.get("lastName");
        client.identification = dataClient.get("identification");
        int id = (int) this.appDataBase.clientDao().insertClient(client);
        if (id > 0) {
            this.presenter.success(id);
        } else {
            this.presenter.error(context.getString(R.string.tittle_client), context.getString(R.string.error_insert_client));
        }
    }
}
