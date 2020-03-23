package db;

import android.provider.BaseColumns;

public class Contrato {
    private static final String TEXT_TYPE = " TEXT ";
    private static final String INT_TYPE = " INTEGER ";

    public Contrato(){
    }

    public static abstract class Nota implements BaseColumns{
        public static final String TABLE_NAME = "nota";
        public static final String COLUMN_TITULO = "titulo";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_ID_TIPO = "id_tipo";

        public static final String[] PROJECTION = {
                Nota._ID, Nota.COLUMN_TITULO, Nota.COLUMN_DESCRICAO
        };

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Nota.TABLE_NAME + "(" +
                        Nota._ID + INT_TYPE + " PRIMARY KEY," +
                        Nota.COLUMN_TITULO + TEXT_TYPE + "," +
                        Nota.COLUMN_DESCRICAO + TEXT_TYPE + "," +
                        Nota.COLUMN_ID_TIPO + INT_TYPE + " REFERENCES " +
                        Tipo.TABLE_NAME + "(" + Tipo._ID + "));";


        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Nota.TABLE_NAME + ";";
    }

    public static abstract class Tipo implements BaseColumns{
        public static final String TABLE_NAME = "tipo";
        public static final String COLUMN_TIPODESC = "tipodesc";

        public static final String[] PROJECTION = {Tipo._ID, Tipo.COLUMN_TIPODESC};

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Tipo.TABLE_NAME + "(" +
                        Tipo._ID + INT_TYPE + " PRIMARY KEY," +
                        Tipo.COLUMN_TIPODESC + TEXT_TYPE + ");";

        public static final String SQL_DROP_ENTRIES =
                "DROP TABLE " + Tipo.TABLE_NAME + ";";
    }
}
