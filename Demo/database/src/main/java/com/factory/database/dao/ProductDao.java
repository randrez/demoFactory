package com.factory.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.factory.database.entity.ProducView;
import com.factory.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    long insertProduct(Product product);

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Update
    void updateProduct(Product product);

    @Query("SELECT p.id, p.code, p.description, p.price, ps.quantity AS quantity FROM product AS p " +
            "INNER JOIN productsale AS ps ON ps.productId = p.id " +
            "WHERE ps.saleId = :saleId")
    List<ProducView> findByProductSale(int saleId);
}
