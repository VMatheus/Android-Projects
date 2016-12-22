package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matheus on 17/11/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String BANCO = "tasks";
    private static final int VERSAO = 1;


    public DataBaseHelper(Context context) {
        super(context, BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table confg(_id integer primary key autoincrement, " +
         //       ")");

        db.execSQL("create table comodos(_id integer primary key autoincrement," +
                "nome text not null )");
        db.execSQL("insert into comodos(nome) values('Área de serviço')");  //1
        db.execSQL("insert into comodos(nome) values('Escritório')");       //2

        db.execSQL("insert into comodos(nome) values('Copa')");             //3
        db.execSQL("insert into comodos(nome) values('Cozinha')");          //4
        db.execSQL("insert into comodos(nome) values('Banheiro')");         //5
        db.execSQL("insert into comodos(nome) values('Sala de estar')");    //6
        db.execSQL("insert into comodos(nome) values('Sala de jantar')");   //7


        //pre-preenchida para testes ou uso
        db.execSQL("create table aparelhos(_id integer primary key autoincrement," +
                "nome text not null," +
                "potencia double not null, " +
                "id_comodo integer not null)");

        //aparelhos padrões
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Batedeira','120','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Exaustor fogão','170','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Exaustor parede','110','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Fogão eletrico 4 bocas','9120','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Forno Microondas','1200','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Freezer','130','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Frigobar','70','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Geladeira 1 porta','90','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Geladeira 2 portas','130','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Lampada Compacta Fluorescente 11W','11','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Lampada Compacta Fluorescente 15W','15','4')");
        db.execSQL("insert into aparelhos(nome, potencia, id_comodo) values('Lampada Compacta Fluorescente 23W','23','4')");










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
