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

import com.xeoh.android.checkboxgroup.CheckBoxGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.iff.pooa20181.yourcook.model.CategoriaIngrediente;
import br.edu.iff.pooa20181.yourcook.model.Ingrediente;
import io.realm.Realm;
import io.realm.RealmResults;

public class FiltroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView cadCatIngred;
    Button btnBuscarIngredientes;
    Spinner spinnerCategoria;

    String item = "";
    private Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        realm = Realm.getDefaultInstance();

        cadCatIngred = (TextView) findViewById(R.id.tvCadCatIngrediente);
        btnBuscarIngredientes = (Button) findViewById(R.id.btnBuscarIngredientes);
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

        btnBuscarIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FiltroActivity.this,ListagemIngredientesActivity.class);
                //intent.putExtra("categoria",item);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // HashMap<CheckBox, String> checkBoxMap = new HashMap<>();
        item = parent.getItemAtPosition(position).toString();

        //traz todos os valores onde a categoria do ingrediente é a que foi selecionada
       /*RealmResults<Ingrediente> realmResults = realm.where(Ingrediente.class).equalTo("categoria", item).findAll();
        List<Ingrediente> ingredientes = realm.copyFromRealm(realmResults);


        CheckBoxGroup<String> checkBoxGroup = new CheckBoxGroup<>(checkBoxMap,
                new CheckBoxGroup.CheckedChangeListener<String>() {
                    @Override
                    public void onCheckedChange(ArrayList<String> values) {

                    }

                });

        //criando layout linear
        LinearLayout anotherLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);


        List<String> strings = new ArrayList<>(ingredientes.size());
        List<CheckBox> cbs = new ArrayList<>(ingredientes.size());
        for (Ingrediente ingrediente : ingredientes) {
            strings.add(ingrediente != null ? ingrediente.getNomeIngrediente().toString() : null);

            cbs.add(strings);
            cbs.setText(ingrediente.getNomeIngrediente().toString());
            anotherLayout.addView(cbs);
            addContentView(anotherLayout, linearLayoutParams);
        }
*/

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
