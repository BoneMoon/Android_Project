package com.example.cm_project;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import db.Contrato;

public class NotasActivity extends AppCompatActivity {

    Second mActicity = new Second();
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);


    }

    public void clickAdd(View view) {
        updateList();
        finish();
    }

    public void updateList(){
        EditText edit_Titulo = (EditText) findViewById(R.id.editTitulo);
        EditText edit_Desc = (EditText) findViewById(R.id.editDesc);
        EditText edit_Tipo = (EditText) findViewById(R.id.editTipo);

        ContentValues cvNota = new ContentValues();
        ContentValues cvTipo = new ContentValues();

        cvNota.put(Contrato.Nota.COLUMN_TITULO, String.valueOf(edit_Titulo));
        cvNota.put(Contrato.Nota.COLUMN_DESCRICAO, String.valueOf(edit_Desc));
        cvTipo.put(Contrato.Tipo.COLUMN_TIPODESC, String.valueOf(edit_Tipo));

        db.insert(Contrato.Tipo.TABLE_NAME, null, cvTipo);
        db.insert(Contrato.Nota.TABLE_NAME, null, cvNota);
        mActicity.refresh();
    }
}
