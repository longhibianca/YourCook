package br.edu.iff.pooa20181.yourcook;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20181.yourcook.adapter.CategoriaRetrofit;
import br.edu.iff.pooa20181.yourcook.adapter.iRetroFitTaco;
import br.edu.iff.pooa20181.yourcook.model.CategoriaIngrediente;
import br.edu.iff.pooa20181.yourcook.model.Ingrediente;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarIngredienteActivity extends AppCompatActivity {

    Button btnSalvar;
    Spinner spinnerCategoria ;
    EditText nomeIngrediente;
    //List<CategoriaIngrediente> categorias;

    private Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ingrediente);

        spinnerCategoria = (Spinner)findViewById(R.id.sCat);

        nomeIngrediente = (EditText) findViewById(R.id.edtNomeIngred);

        btnSalvar = (Button) findViewById(R.id.btnSalvarIngred);

        realm = Realm.getDefaultInstance();

        RealmResults<CategoriaIngrediente> realmResults = realm.where(CategoriaIngrediente.class).findAll();
        List<CategoriaIngrediente> categorias = realm.copyFromRealm(realmResults);

        /*INICIA AQUI O USO DO RETROFIT*/
        //iRetroFitTaco catTaco = iRetroFitTaco.retrofit.create(iRetroFitTaco.class);
       // Call<List<CategoriaIngrediente>> call = new CategoriaRetrofit().getCategoriaService().buscarCategorias();
        //final Call<List<CategoriaIngrediente>> call = catTaco.getCategorias();
        /*call.enqueue(new Callback<List<CategoriaIngrediente>>() {
            @Override
            public void onResponse(Call<List<CategoriaIngrediente>> call, Response<List<CategoriaIngrediente>> response) {
                categorias = response.body();
            }
            @Override
            public void onFailure(Call<List<CategoriaIngrediente>> call, Throwable t) {

                Toast.makeText(getBaseContext(),"Falha ao pegar categorias : ",Toast.LENGTH_LONG).show();
            }
        });
*/
        //USARIA O ATRIBUTO CATEGORIAS DO ONRESPONSE PARA MOSTRAR AS CATEGORIAS NO ADAPTER
        List<String> strings = new ArrayList<>(categorias.size());

        for (CategoriaIngrediente cat : categorias) {
            strings.add(cat != null ? cat.getNomeCategoria().toString() : null);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCategoria.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String nome = nomeIngrediente.getText().toString();

                if(categoria.isEmpty() || (nome.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"Há campo(s) em branco",Toast.LENGTH_LONG).show();
                }
                else
                {
                    salvar();
                }
            }
        });
    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Ingrediente.class).max("id") !=null)
            proximoID = realm.where(Ingrediente.class).max("id").intValue()+1;

        realm.beginTransaction();
        Ingrediente ingred = new Ingrediente();
        ingred.setId(proximoID);
        setEGrava(ingred);

        realm.copyToRealm(ingred);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Ingrediente cadastrado com successo",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Ingrediente ingred){

        ingred.setNomeIngrediente(nomeIngrediente.getText().toString());
        ingred.setCategoria(spinnerCategoria.getSelectedItem().toString());
    }

}
