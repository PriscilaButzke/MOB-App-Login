package br.ulbra.icook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Button cadastrar;
    TextView cliqueAqui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarregarTelaPrincipal();
    }

    public void CarregarTelaPrincipal() {
        setContentView(R.layout.activity_main);
        cliqueAqui = findViewById(R.id.cliqueAqui);
        cliqueAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregarTela2();
            }
        });
    }

    public void CarregarTela2() {
        setContentView(R.layout.activity_form_cadastro);
//        cadastrar = (Button) findViewById(R.id.bt_Cadastrar);
//        cadastrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CarregarTelaPrincipal();
//            }
//        });
    }
}

