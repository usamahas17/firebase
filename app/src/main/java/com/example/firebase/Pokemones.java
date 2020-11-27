package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pokemones extends AppCompatActivity {
    private List<Pokemon> listadepokemones = new ArrayList<Pokemon>();
    ArrayAdapter<Pokemon> adaptador;
    EditText nombre;
    EditText vida;
    EditText ataque;
    EditText defensa;
    ListView lista;
    Pokemon pokemonslecionado;
    FirebaseDatabase fb;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemones);
        nombre = findViewById(R.id.nompokemon);
        vida = findViewById(R.id.vida);
        ataque = findViewById(R.id.ataque);
        defensa =  findViewById(R.id.defensa);
        lista = findViewById(R.id.listadepokemon);
        iniciarfirebase();
        cargarlista();
    }
    private void cargarlista(){
   db.child("pokemon").addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
           listadepokemones.clear();
           for (DataSnapshot d : snapshot.getChildren()){
               Pokemon p = d.getValue(Pokemon.class);
               listadepokemones.add(p);
               adaptador = new ArrayAdapter<Pokemon>(Pokemones.this,android.R.layout.simple_list_item_1,listadepokemones);
               lista.setAdapter(adaptador);

           }

       }

       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }
   });


    }
    public void iniciarfirebase(){
        FirebaseApp.initializeApp(this);
        fb = FirebaseDatabase.getInstance();
        db = fb.getReference();


    }
    public  void guardarlista(View g){
        pokemonslecionado =  new Pokemon();
        pokemonslecionado.setId(UUID.randomUUID().toString());
        pokemonslecionado.setNombre(nombre.getText().toString());
        pokemonslecionado.setAtaque(ataque.getText().toString());
        pokemonslecionado.setDefensa(defensa.getText().toString());
        pokemonslecionado.setVida(vida.getText().toString());
        db.child("pokemon").child(pokemonslecionado.getId()).setValue(pokemonslecionado);
        limpiar();
        Toast.makeText(getApplicationContext(),"pokemon ingresado "+nombre.getText(),Toast.LENGTH_LONG).show();

    }
    public void  limpiar(){
        nombre.setText("");
        ataque.setText("");
        vida.setText("");
        defensa.setText("");
    }
}