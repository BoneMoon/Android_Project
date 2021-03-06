package com.example.cm_project;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Nota.class}, version = 2, exportSchema = false)
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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static NotaRoomDatabase.Callback sRoomDatabaseCallback =
            new NotaRoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NotaDAO mnotaDAO;

        //Integer[] id = {1,2,3};
        String[] titulos = {"Titulo 1", "Titulo 2", "Titulo 3"};
        String[] descricoes = {"Descrição 1", "Descrição 2", "Descrição 3"};
        String[] tipo = {"Tipo 1", "Tipo 2", "Tipo 3"};

        PopulateDbAsync(NotaRoomDatabase db){
            mnotaDAO = db.notaDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //mnotaDAO.deleteAll();

            if(mnotaDAO.getAnyNota().length < 1){
            for(int i = 0; i <= titulos.length - 1; i++){
                Nota nota = new Nota(titulos[i], descricoes[i], tipo[i]);
                mnotaDAO.insert(nota);
                }
            }
            return null;
        }
    }

}
