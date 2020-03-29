package com.example.cm_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNotaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";
    public static final String EXTRA_REPLY_ID =
            "com.android.example.roomwordssample.REPLY_ID";

    private  EditText editTitulo;
    private  EditText editDesc;
    private  EditText editTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_nota);

        editTitulo = findViewById(R.id.editTitulo);
        editDesc = findViewById(R.id.editDesc);
        editTipo = findViewById(R.id.editTipo);

        final Button btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editTitulo.getText()) &&
                TextUtils.isEmpty(editDesc.getText()) &&
                        TextUtils.isEmpty(editTipo.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String titulo = editTitulo.getText().toString();
                    String descricao = editDesc.getText().toString();
                    String tipo = editTipo.getText().toString();
                    String[] nota = {titulo,descricao, tipo};
                    replyIntent.putExtra(EXTRA_REPLY, nota);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
