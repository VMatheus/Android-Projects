package model;

import android.content.ContentValues;
import android.database.Cursor;

import dao.DataBaseHelper;

/**
 * Created by matheus on 17/11/16.
 */

public class Aparelho {
    private Integer id_comodo;
    private Integer _id;
    private String nome;
    private Double potencia;

    public Aparelho(Cursor cursor) {
        set_id(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Aparelhos._ID)));
        setId_comodo(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Aparelhos.ID_COMODO)));
        setNome(cursor.getString(cursor.getColumnIndex(DataBaseHelper.Aparelhos.NOME)));
        setPotencia(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.Aparelhos.POTENCIA)));
    }

    public Aparelho() {

    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Aparelhos._ID, _id);
        values.put(DataBaseHelper.Aparelhos.ID_COMODO, id_comodo);
        values.put(DataBaseHelper.Aparelhos.NOME, nome);
        values.put(DataBaseHelper.Aparelhos.POTENCIA, potencia);
        return values;
    }


    public Integer getId_comodo() {
        return id_comodo;
    }

    public void setId_comodo(Integer id_comodo) {
        this.id_comodo = id_comodo;
    }


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPotencia() {
        return potencia;
    }

    public void setPotencia(Double potencia) {
        this.potencia = potencia;
    }
}
