package com.example.cm_project;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Nota nota);

    @Query("DELETE FROM Nota")
    void deleteAll();

    @Delete
    void deleteNota(Nota nota);

    @Query("SELECT * FROM Nota ORDER BY titulo ASC")
    LiveData<List<Nota>> getAllNotas();

    @Query("SELECT * FROM Nota")
    Nota[] getAnyNota();

    @Update
    void update(Nota... nota);
}
