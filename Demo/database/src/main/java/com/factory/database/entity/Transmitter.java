package com.factory.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transmitter {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String businessName;
    public String rif;
}
