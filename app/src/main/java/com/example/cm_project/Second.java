package com.example.cm_project;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

        import adapter.MyCursorAdapter;
        import db.Contrato;
        import db.DB;

public class Second extends AppCompatActivity {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView lista;
    MyCursorAdapter myadapter;
    private int REQUEST_CODE_OP_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();

        lista = (ListView) findViewById(R.id.lista);

        preencherLista();

        registerForContextMenu(lista);
    }

    private void preencherLista() {

        getCursor();

        /*c = db.query(false, Contrato.Nota.TABLE_NAME, Contrato.Nota.PROJECTION,
                null,null,
                null, null,
                null,null);*/

        myadapter = new MyCursorAdapter(Second.this, c);

        lista.setAdapter(myadapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ativ_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.criar:
                Intent i = new Intent(Second.this, NotasActivity.class);
                //startActivity(i);
                startActivityForResult(i, REQUEST_CODE_OP_1);
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_CODE_OP_1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cont_1, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemPosition = info.position;
        c.moveToPosition(itemPosition);
        int id_nota = c.getInt(c.getColumnIndex(Contrato.Nota._ID));

        switch (item.getItemId()){
            case R.id.editTipo:
                Toast.makeText(Second.this, "Editado", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.remove:
                //Toast.makeText(Second.this, "Remover", Toast.LENGTH_SHORT).show();
                deleteFromDB(id_nota);
                return  true;
            default:
                return  super.onContextItemSelected(item);
        }
    }



    private void deleteFromDB(int id) {
        db.delete(Contrato.Nota.TABLE_NAME, Contrato.Nota._ID + " = ?", new String[]{id+""});
        refresh();
    }

    public void refresh() {
        getCursor();
        myadapter.swapCursor(c);
    }

    private void getCursor() {
       /* String sql = " select " + Contrato.Nota.TABLE_NAME + "."
                + Contrato.Nota._ID + "," + Contrato.Tipo.TABLE_NAME
                + " FROM " + Contrato.Nota.COLUMN_TITULO + "," + Contrato.Nota.COLUMN_DESCRICAO
                + "," + Contrato.Tipo.COLUMN_TIPODESC + " WHERE " + Contrato.Nota.COLUMN_ID_TIPO
                + "=" + Contrato.Tipo.TABLE_NAME + "." + Contrato.Tipo._ID;*/

        String sql = " select " + Contrato.Nota.COLUMN_TITULO + ","
                + Contrato.Nota.COLUMN_DESCRICAO
                + "," + Contrato.Tipo.COLUMN_TIPODESC
                + " FROM " + Contrato.Nota.TABLE_NAME + "."
                + Contrato.Nota._ID + "," + Contrato.Tipo.TABLE_NAME
                + " WHERE " + Contrato.Nota.COLUMN_ID_TIPO
                + "=" + Contrato.Tipo.TABLE_NAME + "." + Contrato.Tipo._ID;

        c = db.rawQuery(sql, null);
    }
}
