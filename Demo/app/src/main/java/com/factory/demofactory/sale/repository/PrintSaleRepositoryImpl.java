package com.factory.demofactory.sale.repository;

import android.content.Context;

import com.factory.database.database.AppDataBase;
import com.factory.database.entity.Client;
import com.factory.database.entity.ProducView;
import com.factory.database.entity.Sale;
import com.factory.database.entity.Transmitter;
import com.factory.demofactory.sale.presenter.PrintSalePresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintSaleRepositoryImpl implements PrintSaleRepository{

    private Context context;
    private AppDataBase appDataBase;
    private PrintSalePresenter presenter;

    public PrintSaleRepositoryImpl(Context context, PrintSalePresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.appDataBase = AppDataBase.getAppDatabase(context);
    }

    @Override
    public void informationSale(int saleId) {
        Sale sale = appDataBase.saleDao().findById(saleId);
        Client client = appDataBase.clientDao().findById(sale.clientId);
        Transmitter transmitter = appDataBase.transmitterDao().findById(sale.transmitterId);
        List<ProducView> products = appDataBase.productDao().findByProductSale(saleId);
        Map<String,String> dataResult = new HashMap<>();
        dataResult.put("business", transmitter.businessName);
        dataResult.put("rif", transmitter.rif);
        dataResult.put("client", client.firstName+" "+client.lastName);
        dataResult.put("ci", client.identification);
        dataResult.put("total", String.valueOf((int) sale.total));
        presenter.success(dataResult, products);
    }
}
