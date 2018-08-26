package br.edu.iff.pooa20181.yourcook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.signin.SignIn;

public class MainActivity extends AppCompatActivity {

    //TextView entrar;
    TextView registrar;
    SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //entrar = (TextView) findViewById(R.id.tvEntrar);
        registrar = (TextView) findViewById(R.id.tvRegistrar);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
       /* entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginUsuarioActivity.class);
                startActivity(intent);
            }
        });*/
        signInButton.setOnClickListener((View.OnClickListener) this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });

        //adicionando config para pedir o email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }
}
