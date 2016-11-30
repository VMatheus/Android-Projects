package model;

/**
 * Created by matheus on 18/11/16.
 */

public class Comodo {

    private Integer id;
    private String nome;

    public Comodo() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Comodo(Integer id, String nome) {
        this.id = id;

        this.nome = nome;
    }
}
