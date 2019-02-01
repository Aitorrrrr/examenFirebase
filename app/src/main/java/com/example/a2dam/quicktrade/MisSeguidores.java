package com.example.a2dam.quicktrade;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MisSeguidores extends AppCompatActivity {

    private RecyclerView rc;
    private RecyclerSeguidores adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;

    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    private ArrayList<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_seguidores);

        nombres = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        bbdd = FirebaseDatabase.getInstance().getReference("seguidores").child(usuario.getUid());

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot aux: dataSnapshot.getChildren())
                {
                    nombres.add(aux.getKey());
                }

                adaptadorRecycler = new RecyclerSeguidores(nombres, MisSeguidores.this);
                rc.setAdapter(adaptadorRecycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        rc = (RecyclerView) findViewById(R.id.recycler_seguidores);
        rvLM = new LinearLayoutManager(this);
        rc.setLayoutManager(rvLM);

        adaptadorRecycler = new RecyclerSeguidores(nombres, this);
        rc.setAdapter(adaptadorRecycler);
    }
}
