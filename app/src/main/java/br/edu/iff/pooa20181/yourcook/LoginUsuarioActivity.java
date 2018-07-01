package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUsuarioActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText edtEmail;
    EditText edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        btnEntrar = (Button) findViewById(R.id.btnEntrarLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().equals("bianca@gmail.com") && edtSenha.getText().toString().equals("123"))
                {
                    Intent intent = new Intent(LoginUsuarioActivity.this,FiltroActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Email ou senha incorretos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
