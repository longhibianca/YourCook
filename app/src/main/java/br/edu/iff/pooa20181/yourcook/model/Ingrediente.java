package br.edu.iff.pooa20181.yourcook.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ingrediente extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String nomeIngrediente;
    private String categoria;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nomeIngrediente, String categoria) {
        this.id = id;
        this.nomeIngrediente = nomeIngrediente;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
