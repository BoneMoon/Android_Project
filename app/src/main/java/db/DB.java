package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notas.db";

    public DB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.Tipo.SQL_CREATE_ENTRIES);
        db.execSQL(Contrato.Nota.SQL_CREATE_ENTRIES);

        db.execSQL("insert into " + Contrato.Tipo.TABLE_NAME + " values(1, 'pessoal');");
        db.execSQL("insert into " + Contrato.Tipo.TABLE_NAME + " values(2, 'trabalho');");
        db.execSQL("insert into " + Contrato.Tipo.TABLE_NAME + " values(3, 'escola');");
        db.execSQL("insert into " + Contrato.Tipo.TABLE_NAME + " values(4, 'acidente');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.Nota.SQL_DROP_ENTRIES);
        db.execSQL(Contrato.Tipo.SQL_DROP_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
