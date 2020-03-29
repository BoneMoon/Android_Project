package com.example.cm_project;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NotaRepository {
    private NotaDAO notaDAO;
    private LiveData<List<Nota>> allNotas;

    NotaRepository(Application application){
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDAO = db.notaDAO();
        allNotas = notaDAO.getAllNotas();
    }

    LiveData<List<Nota>> getAllNotas(){
        return allNotas;
    }

    public void insert(Nota nota){
        new insertAsyncTask(notaDAO).execute(nota);
    }

    public void deleteAll(){
        new deleteAllNotasAsyncTask(notaDAO).execute();
    }

    public void deleteNota(Nota nota){
        new deleteNotaAsyncTask(notaDAO).execute(nota);
    }

    private class insertAsyncTask extends AsyncTask<Nota, Void, Void> {
        private NotaDAO mAsyncTaskDao;

        insertAsyncTask(NotaDAO dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Nota... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private class deleteAllNotasAsyncTask extends AsyncTask<Void, Void, Void>{
        private NotaDAO delDAO;

        deleteAllNotasAsyncTask(NotaDAO dao){
            delDAO = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            delDAO.deleteAll();
            return null;
        }
    }

    private static class deleteNotaAsyncTask extends AsyncTask<Nota, Void, Void> {
        private NotaDAO mAsyncTaskDao;

        deleteNotaAsyncTask(NotaDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Nota... params) {
            mAsyncTaskDao.deleteNota(params[0]);
            return null;
        }
    }
}
