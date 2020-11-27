package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Registro extends AppCompatActivity {
    EditText nombre;
    EditText clave;
    FirebaseDatabase fb;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = findViewById(R.id.nombre);
        clave = findViewById(R.id.clave);
        iniciarfirebase();
    }
    public  void iniciarfirebase(){
        FirebaseApp.initializeApp(this);
        fb = FirebaseDatabase.getInstance();
        db= fb.getReference();

    }
    public void guardar(View m){
        Intent in = new Intent(getApplicationContext(),Pokemones.class);
        Usuario u = new Usuario();
        u.setId(UUID.randomUUID().toString());
        u.setNombre(nombre.getText().toString());
        u.setClave(clave.getText().toString());
        db.child("usuario").child(u.getId()).setValue(u);
        limpiar();
        Toast.makeText(getApplicationContext(),"usuario ingrsado "+nombre.getText(),Toast.LENGTH_LONG).show();
        startActivity(in);


    }
    public void limpiar(){
        nombre.setText("");
        clave.setText("");
    }

}