package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20181.yourcook.model.CategoriaIngrediente;
import io.realm.Realm;
import io.realm.RealmResults;

public class FiltroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView cadCatIngred;
    Button btnBuscarReceita;
    Spinner spinnerCategoria;

    private Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        realm = Realm.getDefaultInstance();

        cadCatIngred = (TextView) findViewById(R.id.tvCadCatIngrediente);
        btnBuscarReceita = (Button) findViewById(R.id.btnBuscarReceita);
        spinnerCategoria = (Spinner) findViewById(R.id.sCatIngredientes);

        spinnerCategoria.setOnItemSelectedListener(this);

        //preencher spinner com valor do banco
        RealmResults<CategoriaIngrediente> realmResults = realm.where(CategoriaIngrediente.class).findAll();
        List<CategoriaIngrediente> categorias = realm.copyFromRealm(realmResults);

        List<String> strings = new ArrayList<>(categorias.size());
        for (CategoriaIngrediente cat : categorias) {
            strings.add(cat != null ? cat.getNomeCategoria().toString() : null);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCategoria.setAdapter(adapter);

        cadCatIngred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiltroActivity.this,CadastrarCatIngredienteActivity.class);
                startActivity(intent);
            }
        });

        btnBuscarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiltroActivity.this,ListagemRefeicoesActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCadIngrediente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiltroActivity.this,CadastrarIngredienteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
