package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Aparelho;
import model.Comodo;

/**
 * Created by matheus on 17/11/16.
 */


public class ComodoDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;


    public ComodoDAO(Context context) {

        dataBaseHelper = new DataBaseHelper(context);

    }


    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();

        }
        return database;

    }

    private Comodo criaComodo(Cursor cursor) {
        Comodo model = new Comodo(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Comodos._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Comodos.NOME))

        );
        return model;
    }


    public List<Comodo> listaComodos() {

        Cursor cursor = getDatabase().query(DataBaseHelper.Comodos.TABELA, DataBaseHelper.Comodos.COLUNAS, null, null, null, null, null);

        Log.v("","Quantidade de comodos"+cursor.getCount());
        List<Comodo> comodos = new ArrayList<Comodo>();

        while (cursor.moveToNext()) {
            Comodo model = criaComodo(cursor);
            comodos.add(model);

        }
        cursor.close(); // liberando recurso, memoria
        return comodos;
    }


    public long salvarComodo(Comodo comodo) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Comodos.NOME, comodo.getNome());
        if (comodo.getId() != null) {
            return getDatabase().update(DataBaseHelper.Comodos.TABELA, values, "_id=?", new String[]{comodo.getId().toString()});

        }
        return getDatabase().insert(DataBaseHelper.Comodos.TABELA, null, values);

    }

    public boolean removeComodo(int id) {
        return getDatabase().delete(DataBaseHelper.Comodos.TABELA, "_id=?", new String[]{Integer.toString(id)}) > 0;


    }

    public Comodo buscaPorId(int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Comodos.TABELA, DataBaseHelper.Comodos.COLUNAS, "" +
                "_id=?", new String[]{Integer.toString(id)}, null, null, null);
        if (cursor.moveToNext()) {
            Comodo model = criaComodo(cursor);

            cursor.close();
            return model;


        }
        return null;

    }

    public void fechar() {

        dataBaseHelper.close();
        database = null;
    }


}





