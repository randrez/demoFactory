package com.factory.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductSale {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int productId;
    public int saleId;
    public int quantity;
}
