package com.example.cm_project;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Nota nota);

    @Query("DELETE FROM nota")
    void deleteAll();

    @Query("SELECT * FROM nota ORDER BY titulo ASC")
    LiveData<List<Nota>> getAllNotas();
}
