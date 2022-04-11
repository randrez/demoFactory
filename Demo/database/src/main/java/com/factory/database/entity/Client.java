package com.factory.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Client {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String firstName;
    public String lastName;
    public String identification;
}
