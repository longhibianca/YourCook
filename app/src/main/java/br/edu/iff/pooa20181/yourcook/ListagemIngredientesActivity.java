package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa20181.yourcook.adapter.ListaIngredienteAdapter;
import br.edu.iff.pooa20181.yourcook.model.Ingrediente;
import br.edu.iff.pooa20181.yourcook.model.ItemIngredienteDTO;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListagemIngredientesActivity extends AppCompatActivity {

    String retorno;
    private Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_ingredientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListagemIngredientesActivity.this,CadastrarIngredienteActivity.class);
                startActivity(intent);
            }
        });

        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.listViewIngredientes);

        // Initiate listview data.
        final List<ItemIngredienteDTO> initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        final ListaIngredienteAdapter listViewDataAdapter = new ListaIngredienteAdapter(getApplicationContext(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // pega o objeto selecionado
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // converte o objeto selecionado para DTO object.
                ItemIngredienteDTO itemDto = (ItemIngredienteDTO)itemObject;

                // pega a referencia da checkbox da view
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.item_ingrediente);

                // faz ficar checked ou unchecked
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

            }
        });



    }

    // Return an initialize list of ListViewItemDTO.
    private List<ItemIngredienteDTO> getInitViewItemDtoList()
    {
        //pega a categoria selecionada na pagina de filtro
        Intent intent = getIntent();
        retorno = (String) intent.getSerializableExtra("categoria");

        RealmResults<Ingrediente> realmResults = realm.where(Ingrediente.class).equalTo("categoria", retorno).findAll();
        List<Ingrediente> ingredientes = realm.copyFromRealm(realmResults);

        List<String> strings = new ArrayList<>(ingredientes.size());
        for (Ingrediente ing : ingredientes) {
            strings.add(ing != null ? ing.getNomeIngrediente().toString() : null);
        }


        List<ItemIngredienteDTO> ret = new ArrayList<ItemIngredienteDTO>();

        //itera na string para preencher a lista de checkbox da view
        for(String s : strings)
        {
            String itemText = s;

            ItemIngredienteDTO dto = new ItemIngredienteDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

}
