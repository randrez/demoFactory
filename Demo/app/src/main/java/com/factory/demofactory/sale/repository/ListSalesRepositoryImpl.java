package com.factory.demofactory.sale.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Client;
import com.factory.database.entity.Sale;
import com.factory.database.entity.Transmitter;
import com.factory.demofactory.R;
import com.factory.demofactory.sale.presenter.ListSalesPresenter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListSalesRepositoryImpl implements ListSalesRepository {

    private Context context;
    private AppDataBase appDataBase;
    private ListSalesPresenter presenter;

    public ListSalesRepositoryImpl(Context context, ListSalesPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void getSales() {
        try {
            List<JSONObject> objects = new ArrayList<>();
            List<Sale> sales = appDataBase.saleDao().getAll();
            for (Sale sale : sales) {
                Client client = appDataBase.clientDao().findById(sale.clientId);
                Transmitter transmitter = appDataBase.transmitterDao().findById(sale.transmitterId);
                JSONObject object = new JSONObject();
                object.put("id", sale.id);
                object.put("client", client.firstName + " " + client.lastName);
                object.put("business", transmitter.businessName);
                object.put("total", String.valueOf((int) sale.total));
                objects.add(object);
            }
            presenter.success(objects);
        } catch (Exception e) {
            e.printStackTrace();
            presenter.error(context.getString(R.string.list_sales), e.getMessage());
        }
    }
}
