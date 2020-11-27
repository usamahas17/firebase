package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Registro us;
    EditText nombre;
    EditText clave;
    FirebaseAuth mAuth;
    String nom;
    String cla;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.usuario);
        clave = findViewById(R.id.contraseña);
        mAuth = FirebaseAuth.getInstance();



    }
    public void registrar(View n){
        Intent in =  new Intent(getApplicationContext(),Registro.class);
        startActivity(in);
    }
    public void entrar (View k){
        nom =  nombre.getText().toString();
        cla =  clave.getText().toString();
        if (!nom.isEmpty() && !cla.isEmpty()){
            login();
        }else{
            Toast.makeText(getApplicationContext(),"campos vacios",Toast.LENGTH_LONG).show();
        }


    }
    private void login(){
        mAuth.signInWithEmailAndPassword(nom,cla).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,Pokemones.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"usuario no reconocido",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}