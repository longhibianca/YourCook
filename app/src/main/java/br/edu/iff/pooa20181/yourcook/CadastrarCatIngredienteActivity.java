package br.edu.iff.pooa20181.yourcook;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa20181.yourcook.model.CategoriaIngrediente;
import io.realm.Realm;

public class CadastrarCatIngredienteActivity extends AppCompatActivity {

    EditText nomeCategoria, descricao;
    Button btnSalvar;

    private Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cat_ingrediente);

        nomeCategoria = (EditText) findViewById(R.id.edtNomeCategoria);
        descricao = (EditText) findViewById(R.id.edtDescricaoCategoria);
        btnSalvar = (Button) findViewById(R.id.btnSalvarCat);

        realm = Realm.getDefaultInstance();

        btnSalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(nomeCategoria.getText().toString().isEmpty() || (descricao.getText().toString().isEmpty()))
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
        if(realm.where(CategoriaIngrediente.class).max("id") !=null)
            proximoID = realm.where(CategoriaIngrediente.class).max("id").intValue()+1;

        realm.beginTransaction();
        CategoriaIngrediente categoria = new CategoriaIngrediente();
        categoria.setId(proximoID);
        setEGrava(categoria);

        realm.copyToRealm(categoria);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Categoria cadastrada com successo",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(CategoriaIngrediente categoria){

        categoria.setNomeCategoria(nomeCategoria.getText().toString());
        categoria.setDescricao(descricao.getText().toString());
    }

}
