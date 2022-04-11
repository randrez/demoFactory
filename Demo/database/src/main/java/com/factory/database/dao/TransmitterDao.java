package com.factory.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.factory.database.entity.Transmitter;

import java.util.List;

@Dao
public interface TransmitterDao {

    @Insert
    long insertTransmitter(Transmitter transmitter);

    @Query("SELECT * FROM transmitter")
    List<Transmitter> getAll();

    @Query("SELECT * FROM transmitter WHERE id = :id")
    Transmitter findById(int id);

    @Query("SELECT EXISTS(SELECT * FROM transmitter)")
    boolean hasTransmitter();

}
