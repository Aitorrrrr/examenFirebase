package com.example.a2dam.quicktrade;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity implements RecyclerUsuarios.interfazUsuarios{

    private RecyclerView rc;
    private RecyclerUsuarios adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    private DatabaseReference bbdd;
    private DatabaseReference bbddSeguidores;

    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    private ArrayList<Usuario> arrayUsers;
    private ArrayList<String> arrayIDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        arrayIDS = new ArrayList<>();
        arrayUsers = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
        bbddSeguidores = FirebaseDatabase.getInstance().getReference("seguidores");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot aux: dataSnapshot.getChildren())
                {
                    Usuario auxiliar = aux.getValue(Usuario.class);

                    if (aux.getKey().compareToIgnoreCase(usuario.getUid())==0)
                    {
                        Log.d("MIO", "User que esta dentro");
                    }
                    else
                    {
                        arrayUsers.add(auxiliar);
                        arrayIDS.add(aux.getKey());
                    }
                }

                adaptadorRecycler = new RecyclerUsuarios(arrayUsers,arrayIDS, Usuarios.this);
                rc.setAdapter(adaptadorRecycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rc = (RecyclerView) findViewById(R.id.recycler_usuarios);
        rvLM = new LinearLayoutManager(this);
        rc.setLayoutManager(rvLM);

        adaptadorRecycler = new RecyclerUsuarios(arrayUsers,arrayIDS, Usuarios.this);
        rc.setAdapter(adaptadorRecycler);
    }

    @Override
    public void userSeguido(String id, String nombre) {
        Log.d("MIO", "siguiendo a user "+id);

        bbddSeguidores.child(id).child(usuario.getUid()).setValue(nombre);
        //bbddSeguidores.child(id).child(usuario.getUid()).child("nombre").setValue(usuario.getDisplayName());
    }
}
