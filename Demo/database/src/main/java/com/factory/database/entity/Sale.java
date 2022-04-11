package com.factory.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sale {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int transmitterId;
    public int clientId;
    public double total;
}
