package com.example.cm_project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Nota")
public class Nota {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "titulo")
    private String titulo;

    @NonNull
    @ColumnInfo(name = "descricao")
    private String descricao;

    @NonNull
    @ColumnInfo(name = "tipoDescricao")
    private String tipoDescricao;


    public Nota(@NonNull String titulo, @NonNull String descricao,
                @NonNull String tipoDescricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoDescricao = tipoDescricao;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getTipoDescricao(){
        return this.tipoDescricao;
    }
}

