package com.factory.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.factory.database.entity.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Insert
    long insertClient(Client client);

    @Query("SELECT * FROM client")
    List<Client> getAll();

    @Query("SELECT * FROM client WHERE id = :id")
    Client findById(int id);

    @Query("SELECT EXISTS(SELECT * FROM client)")
    boolean hasClient();
}
