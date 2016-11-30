package model;

/**
 * Created by matheus on 03/11/16.
 */
public class Filho {


    private Integer _id;
    private String nome;
    private String saldo ;

    //construtores
    public Filho(){

    }

    public Filho(Integer id , String nome, String saldo){
        this._id = id;
        this.nome= nome;
        this.saldo= saldo;
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

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
