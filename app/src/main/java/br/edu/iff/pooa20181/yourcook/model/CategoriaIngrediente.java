package br.edu.iff.pooa20181.yourcook.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CategoriaIngrediente extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String nomeCategoria;
    private String descricao;

    public CategoriaIngrediente() {
    }

    public CategoriaIngrediente(int id, String nomeCategoria, String descricao) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   /* int _id;
    String categoria;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    } */
}
