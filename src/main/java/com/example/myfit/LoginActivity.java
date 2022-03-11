package com.example.myfit;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;
import androidx.biometric.BiometricPrompt;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText  email, password ;
    ImageView empreinte;
    BiometricPrompt prompt;
    BiometricPrompt.PromptInfo bio;
    Executor e;
    BDD dbHelper = new BDD(this, "Utilisateurs", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.cirLoginButton);
        email = (EditText) findViewById(R.id.LeditTextEmail);
        password = (EditText) findViewById(R.id.LeditTextPassword);
        empreinte = (ImageView) findViewById(R.id.empreinte);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper.connexion(email.getText().toString(), password.getText().toString()) == true) {
                    Intent intent = new Intent(LoginActivity.this, CategoriesActivity.class);
                    intent.putExtra("email", email.getText().toString());
                    startActivity(intent);
                } else {
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                }
            }
        });


        //Empreinte

        e = ContextCompat.getMainExecutor(this);

        prompt = new BiometricPrompt(LoginActivity.this,e,new BiometricPrompt.AuthenticationCallback(){
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(LoginActivity.this,"Authentification réussie", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("email",email.getText().toString());
                startActivity(i);
                finish();
            }
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(LoginActivity.this, "Authentification echouée", Toast.LENGTH_SHORT).show();
            }
        });
        bio = new BiometricPrompt.PromptInfo.Builder().setTitle("Authentification biométrique").setNegativeButtonText("Connexion avec mes identifiants").build();
        empreinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prompt.authenticate(bio);
            }
        });

    }
        public void onLoginClick(View View){
        startActivity(new Intent(this, RegisterActivity.class));
       // overridePendingTransition(R.anim.slide_in_right,R.anim.stay);


    }
}