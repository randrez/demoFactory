package com.factory.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String code;
    public String description;
    public double price;
}
