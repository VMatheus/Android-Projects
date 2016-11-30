package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Aparelho;
import model.Comodo;

/**
 * Created by matheus on 19/11/16.
 */

public class AparelhoDAO {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public AparelhoDAO(Context context) {

        dataBaseHelper = new DataBaseHelper(context);


    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();

        }
        return database;
    }

    public ArrayList<Aparelho> getAllAparelhos() {
        Cursor cursor = getDatabase().query(DataBaseHelper.Aparelhos.TABELA, DataBaseHelper.Aparelhos.COLUNAS, null, null, null, null, null);
        ArrayList<Aparelho> aparelhos = new ArrayList<>();
        while (cursor.moveToNext()) {
            aparelhos.add(new Aparelho(cursor));

        }
        cursor.close();
        return  aparelhos;

    }

    public ArrayList<Aparelho> getAparelhosComId(Integer idComodo) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Aparelhos.TABELA, null, DataBaseHelper.Aparelhos.ID_COMODO + "=?", new String[]{idComodo.toString()}, null, null, null);
        Log.v("Aparelho", "" + cursor.getCount());
        ArrayList<Aparelho> aparelhos = new ArrayList<>();

        while (cursor.moveToNext()) {
            aparelhos.add(new Aparelho(cursor));

        }

        cursor.close();
        return aparelhos;


    }


    public void salva(Aparelho aparelho) {
        dataBaseHelper.getWritableDatabase().insert(DataBaseHelper.Aparelhos.TABELA, null, aparelho.getValues());

    }


    public boolean removeAparelho(int id) {
        return getDatabase().delete(DataBaseHelper.Aparelhos.TABELA, "_id=?", new String[]{Integer.toString(id)}) > 0;

    }


    public void fechar() {
        dataBaseHelper.close();
        database = null;

    }


}
