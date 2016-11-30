package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Regra;

/**
 * Created by matheus on 03/11/16.
 */

public class RegraDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public RegraDAO(Context context) {
        dataBaseHelper = new DataBaseHelper(context);

    }


    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();

        }
        return database;
    }


    private Regra criaRegra(Cursor cursor) {
        Regra model = new Regra(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Regras._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Regras.DESCRICAO)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Regras.VALOR))
        );
        return model;

    }

    public List<Regra> listaRegras() {
        Cursor cursor = getDatabase().query(DataBaseHelper.Regras.TABELA, DataBaseHelper.Regras.COLUNAS, null, null, null, null, null);
        @SuppressWarnings("Convert2Diamond") List<Regra> regras = new ArrayList<Regra>();
        while (cursor.moveToNext()) {
            Regra model = criaRegra(cursor);
            regras.add(model);
        }
        cursor.close();
        return regras;
    }

    public long salvar(Regra regra) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Regras.DESCRICAO, regra.getDescricao());
        values.put(DataBaseHelper.Regras.VALOR, regra.getValor());
        if (regra.get_id() != null) {
            return getDatabase().update(DataBaseHelper.Regras.TABELA, values, "_id=?", new
                    String[]{regra.get_id().toString()});

        }
        return getDatabase().insert(DataBaseHelper.Regras.TABELA, null, values);
    }

    public boolean removeRegra(int id) {
        return getDatabase().delete(DataBaseHelper.Regras.TABELA, "_id=?",
                new String[]{Integer.toString(id)}) > 0;

    }

    public Regra buscaPorId(int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Regras.TABELA,
                DataBaseHelper.Regras.COLUNAS, "_id=?", new String[]{Integer.toString(id)
                }, null, null, null);
        if (cursor.moveToNext()) {
            Regra model = criaRegra(cursor);
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
