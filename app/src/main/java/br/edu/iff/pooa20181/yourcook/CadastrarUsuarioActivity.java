package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Função não implementada",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CadastrarUsuarioActivity.this,LoginUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
