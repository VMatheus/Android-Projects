package model;

/**
 * Created by matheus on 03/11/16.
 */

public class Regra {

    private Integer _id;
    private String descricao;
    private String valor;

    public Regra() {

    }

    public Regra(Integer id, String descricao, String valor) {
        this._id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
