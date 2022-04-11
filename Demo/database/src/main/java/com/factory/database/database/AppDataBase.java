package com.factory.database.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.factory.database.dao.ClientDao;
import com.factory.database.dao.ProductDao;
import com.factory.database.dao.ProductSaleDao;
import com.factory.database.dao.SaleDao;
import com.factory.database.dao.TransmitterDao;
import com.factory.database.entity.Client;
import com.factory.database.entity.Product;
import com.factory.database.entity.ProductSale;
import com.factory.database.entity.Sale;
import com.factory.database.entity.Transmitter;

@Database(entities = {Client.class,
        Transmitter.class,
        Product.class,
        Sale.class,
        ProductSale.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract ClientDao clientDao();

    public abstract TransmitterDao transmitterDao();

    public abstract ProductDao productDao();

    public abstract SaleDao saleDao();

    public abstract ProductSaleDao productSaleDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "demo-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
