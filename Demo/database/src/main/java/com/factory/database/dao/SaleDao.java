package com.factory.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.factory.database.entity.Sale;

import java.util.List;

@Dao
public interface SaleDao {

    @Insert
    long insertSale(Sale sale);

    @Query("SELECT * FROM sale WHERE id =:id")
    Sale findById(int id);

    @Query("SELECT * FROM sale")
    List<Sale> getAll();

    @Query("SELECT EXISTS(SELECT * FROM sale)")
    boolean hasSale();
}
