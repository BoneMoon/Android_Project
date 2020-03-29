package com.example.cm_project;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import adapters.NotaListAdapter;

public class NotaActivity extends AppCompatActivity {

    private NotaViewModel mNotaViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_UPDATE_NOTA = "extra_nota_to_be_updated";
    public static final String EXTRA_DATA_ID = "extra_data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.lista);
        final NotaListAdapter adapter = new NotaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNotaViewModel = ViewModelProviders.of(this).get(NotaViewModel.class);

        mNotaViewModel.getAllNotas().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(@Nullable final List<Nota> notas) {
                // Update the cached copy of the words in the adapter.
                adapter.setNotas(notas);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotaActivity.this, NewNotaActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int diretion) {
                        int position = viewHolder.getAdapterPosition();
                        Nota mnota = adapter.getNotaPosition(position);
                        Toast.makeText(NotaActivity.this, "Apagar " +
                            mnota.getTitulo(), Toast.LENGTH_SHORT).show();

                        mNotaViewModel.deleteNota(mnota);
                    }
                });
        helper.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NotaListAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Nota nota = adapter.getNotaPosition(position);
                launchUpdateNotaActivity(nota);
            }
        });
    }

    public void launchUpdateNotaActivity( Nota nota) {
        Intent intent = new Intent(this, NewNotaActivity.class);
        intent.putExtra(EXTRA_DATA_UPDATE_NOTA, nota.getTitulo());
        intent.putExtra(EXTRA_DATA_ID, nota.getId());
        startActivityForResult(intent, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] nota = data.getStringArrayExtra(NewNotaActivity.EXTRA_REPLY);
            String titulo = nota[0];
            String descricao = nota[1];
            String tipo = nota[2];
            int id = data.getIntExtra(NewNotaActivity.EXTRA_REPLY_ID, -1);

            if(id != -1){
                mNotaViewModel.insert(new Nota(titulo,descricao,tipo));
            }

            Nota notaFinal = new Nota(titulo,descricao,tipo);
            mNotaViewModel.insert(notaFinal);
        } else if(requestCode == UPDATE_WORD_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK){
            String[] nota = data.getStringArrayExtra(NewNotaActivity.EXTRA_REPLY);
            String titulo = nota[0];
            String descricao = nota[1];
            String tipo = nota[2];
            int id = data.getIntExtra(NewNotaActivity.EXTRA_REPLY_ID, -1);

            if(id != -1){
                mNotaViewModel.updateNota(new Nota(titulo,descricao,tipo));
            } else {
                Toast.makeText(this, "Não foi possivel fazer o update",
                        Toast.LENGTH_LONG).show();
            }
        }

        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Nota não pode ser guardada, todos os campos têm que ser preenchidos",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.deleteAll){
            Toast.makeText(this, "Apagado as notas...", Toast.LENGTH_SHORT).show();

            mNotaViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
