package com.example.a2dam.quicktrade;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerSeguidores extends RecyclerView.Adapter<RecyclerSeguidores.SeguidoresViewHolder> {

    private ArrayList<String> arraySeguidores;

    public static class SeguidoresViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView nombre;;

        public Context context;
        public View v;

        public SeguidoresViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.seguidores_nombreUser);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerSeguidores(ArrayList<String> array, MisSeguidores context)
    {
        this.arraySeguidores = array;
    }

    @NonNull
    @Override
    public SeguidoresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_seguidores,viewGroup, false);

        return new SeguidoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SeguidoresViewHolder seguidoresViewHolder, int i) {
        seguidoresViewHolder.nombre.setText(arraySeguidores.get(i));
    }

    @Override
    public int getItemCount() {
        return arraySeguidores.size();
    }
}
