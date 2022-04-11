package com.factory.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.factory.database.entity.ProductSale;

import java.util.List;

@Dao
public interface ProductSaleDao {

    @Insert
    void insertProductSale(ProductSale productSale);

    @Insert
    void insertAll(List<ProductSale> productSaleList);
}
