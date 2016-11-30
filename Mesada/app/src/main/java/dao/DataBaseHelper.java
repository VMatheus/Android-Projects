package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matheus on 03/11/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BANCO = "tasks";
    private static final int VERSAO = 2;

    public DataBaseHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table filhos (_id integer primary key autoincrement," +
                "nome text not null, saldo text not null, image  blob)");


        db.execSQL("create table regras(_id integer primary key autoincrement," +
                "descricao text not null, valor text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Filhos {
        public static final String TABELA = "filhos";
        public static final String _ID = "_id";
        public static final String NOME = "nome";

        public static final String SALDO = "saldo";
        public static final String[] COLUNAS = new String[]{
                _ID, NOME, SALDO
        };
    }

    public static class Regras {
        public static final String TABELA = "regras";
        public static final String _ID = "_id";
        public static final String DESCRICAO = "descricao";
        public static final String VALOR = "valor";
        public static final String[] COLUNAS = new String[]{
                _ID, DESCRICAO, VALOR
        };

    }
}
