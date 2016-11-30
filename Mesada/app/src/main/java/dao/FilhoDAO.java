package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Filho;

/**
 * Created by matheus on 03/11/16.
 */

public class FilhoDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public FilhoDAO(Context context) {

        dataBaseHelper = new DataBaseHelper(context);


    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();

        }
        return database;

    }

    private Filho criaFilho(Cursor cursor) {
        Filho model = new Filho(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Filhos._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Filhos.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Filhos.SALDO))

        );
        return model;
    }

    public List<Filho> listaFilhos() {

        Cursor cursor = getDatabase().query(DataBaseHelper.Filhos.TABELA, DataBaseHelper.Filhos.COLUNAS, null, null, null, null, null);
        List<Filho> filhos = new ArrayList<Filho>();
        while (cursor.moveToNext()) {
            Filho model = criaFilho(cursor);
            filhos.add(model);

        }
        cursor.close();
        return filhos;


    }

    public long salvar(Filho filho) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Filhos.NOME, filho.getNome());
        values.put(DataBaseHelper.Filhos.SALDO, filho.getSaldo());
        if (filho.get_id() != null) {
            return getDatabase().update(DataBaseHelper.Filhos.TABELA, values, "_id= ?", new
                    String[]{filho.get_id().toString()});

        }
        return getDatabase().insert(DataBaseHelper.Filhos.TABELA, null, values);

    }
    public boolean removeFilho(int id) {
        return getDatabase().delete(DataBaseHelper.Filhos.TABELA, "_id=?", new String[]{Integer.toString(id)}) > 0;
    }
    public Filho buscarPorId(int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Filhos.TABELA,
                DataBaseHelper.Filhos.COLUNAS, "_id=?", new String[]{Integer.toString(id)
                }, null, null, null);
        if (cursor.moveToNext()) {
            Filho model = criaFilho(cursor);
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
