package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matheus on 17/11/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String BANCO = "tasks";
    private static final int VERSAO = 3;


    public DataBaseHelper(Context context) {
        super(context, BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table confg(_id integer primary key autoincrement, " +
                ")");

        db.execSQL("create table comodos(_id integer primary key autoincrement," +
                "nome text not null )");

        //pre-preenchida para testes ou uso
        db.execSQL("create table aparelhos(_id integer primary key autoincrement," +
                "nome text not null," +
                "potencia double not null, " +
                "id_comodo integer not null)");





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public static class Comodos {
        public static final String TABELA = "comodos";
        public static final String _ID = "_id";
        public static final String NOME = "nome";


        public static final String[] COLUNAS = new String[]{
                _ID, NOME
        };
    }

    public static class Aparelhos {
        public static final String TABELA = "aparelhos";
        public static final String _ID = "_id";
        public static final String ID_COMODO = "id_comodo";
        public static final String NOME = "nome";
        public static final String POTENCIA = "potencia";
        public static final String[] COLUNAS = new String[]{
                _ID, ID_COMODO, NOME, POTENCIA
        };


    }
}
