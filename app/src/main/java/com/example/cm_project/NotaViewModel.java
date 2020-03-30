package com.example.cm_project;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

public class NotaViewModel extends AndroidViewModel {

    private NotaRepository mRepository;
    private LiveData<List<Nota>> allNotas;

    public NotaViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NotaRepository(application);
        allNotas = mRepository.getAllNotas();
    }

    LiveData<List<Nota>> getAllNotas(){
        return allNotas;
    }

    public void insert(Nota nota){
        mRepository.insert(nota);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteNota(Nota nota){
        mRepository.deleteNota(nota);
    }

    public void updateNota(Nota nota){
        mRepository.updateNota(nota);
    }

    public LiveData<Nota> getNotaId(int id){
        return mRepository.getNotaID(id);
    }
}
