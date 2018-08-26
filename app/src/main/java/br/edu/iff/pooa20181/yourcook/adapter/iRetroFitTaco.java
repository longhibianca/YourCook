package br.edu.iff.pooa20181.yourcook.adapter;

import java.util.List;

import br.edu.iff.pooa20181.yourcook.model.CategoriaIngrediente;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface iRetroFitTaco {

    @GET("/categorias")
    Call<List<CategoriaIngrediente>> getCategorias(@Path("categoria") String categoria);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://taco-alimentos.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
