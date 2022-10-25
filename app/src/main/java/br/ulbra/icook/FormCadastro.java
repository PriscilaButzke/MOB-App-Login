package br.ulbra.icook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import de.hdodenhof.circleimageview.CircleImageView;

public class FormCadastro extends AppCompatActivity {
    private CircleImageView fotoUsuario;
    private Button bt_selecionarFoto, bt_cadastrar;
    private EditText txt_nome, txt_Email, txt_senha;
    private TextView txt_mensagemErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        IniciarComponentes();
        txt_nome.addTextChangedListener(cadastroTextWatcher);
        txt_Email.addTextChangedListener(cadastroTextWatcher);
        txt_senha.addTextChangedListener(cadastroTextWatcher);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastrarUsuario(v);
    Log.d("Cadastrar","clicou no cadastrar");
            }
        });
    }
    public void CadastrarUsuario(View view){
        String email = txt_Email.getText().toString();
        String senha = txt_senha.getText().toString();

        Log.d("Entrou","entrou no cadastrar usuário");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(view, "Cadastro realizado com sucesso!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                    snackbar.show();
                }else {

                    String erro;

                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Coloque uma senha com no minimo 6 caracteres!";
                        Log.d("Senha","Coloque uma senha com no minimo 6 caracteres!");

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail Invalido";
                        Log.d("E-mail","E-mail Invalido");

                    }catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi criada!";
                        Log.d("Conta","Esta conta já foi criada!");

                    }catch (FirebaseNetworkException e){
                        erro = "Sem conexão com a Internet!";
                        Log.d("Conexão","Sem conexão com a Internet!");

                    }catch (Exception e){
                        erro = "Erro ao cadastra o usúario!";
                        Log.d("Erro","Erro ao cadastra o usúario!");
                    }
                    txt_mensagemErro.setText(erro);
                }
            }
        });
    }

    public void IniciarComponentes(){
        fotoUsuario = findViewById(R.id.fotoUsuario);
        bt_selecionarFoto = findViewById(R.id.bt_selecionarFoto);
        txt_nome = findViewById(R.id.txt_nome);
        txt_Email = findViewById(R.id.txt_Email);
        txt_senha = findViewById(R.id.txt_senha);
        txt_mensagemErro = findViewById(R.id.txt_mensagemErro);
        bt_cadastrar = findViewById(R.id.bt_Cadastrar);
    }

    TextWatcher cadastroTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String nome = txt_nome.getText().toString();
            String email = txt_Email.getText().toString();
            String senha = txt_senha.getText().toString();

            if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()){
                bt_cadastrar.setEnabled(true);
                bt_cadastrar.setBackgroundColor(getResources().getColor(R.color.teal_200));
            }else {
                bt_cadastrar.setEnabled(false);
                bt_cadastrar.setBackgroundColor(getResources().getColor(R.color.purple_200));
            }
        }
        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

}

