package adapter;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cm_project.R;

import db.Contrato;


public class MyCursorAdapter extends CursorAdapter {
    private Context mContext;
    private int id;
    private Cursor mCursor;

    public MyCursorAdapter (Context context, Cursor c){
        super(context, c, 0);
        mContext = context;
        mCursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.linha, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txt1 = (TextView) view.findViewById(R.id.titulo);
        TextView txt2 = (TextView) view.findViewById(R.id.descricao);
        TextView txt3 = (TextView) view.findViewById(R.id.tipo);

        txt1.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Nota.COLUMN_TITULO)));
        txt2.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Nota.COLUMN_DESCRICAO)));
        //txt3.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Nota.COLUMN_ID_TIPO)));
    }


}
