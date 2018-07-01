package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FiltroActivity extends AppCompatActivity {

    TextView cadCatIngred;
    Button btnBuscarReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);

        cadCatIngred = (TextView) findViewById(R.id.tvCadCatIngrediente);
        btnBuscarReceita = (Button) findViewById(R.id.btnBuscarReceita);

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
}
