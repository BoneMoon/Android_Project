package com.example.cm_project;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Nota.class}, version = 1, exportSchema = false)
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract NotaDAO notaDAO();
    private static NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotaRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Criação da base de dados
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "nota_database")
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
