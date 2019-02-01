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

public class RecyclerUsuarios extends RecyclerView.Adapter<RecyclerUsuarios.UsuariosViewHolder> {

    private ArrayList<Usuario> arrayUsers;
    private ArrayList<String> arrayIDS;
    private interfazUsuarios interfaz;

    public static class UsuariosViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView nombre;
        public TextView id;
        public Button seguir;

        public Context context;
        public View v;

        public UsuariosViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.rc_user_nombre);
            id = (TextView) itemView.findViewById(R.id.rc_user_id);
            seguir = (Button) itemView.findViewById(R.id.rc_user_seguir);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerUsuarios(ArrayList<Usuario> array, ArrayList<String> arrayIDS, Usuarios context)
    {
        this.arrayUsers = array;
        this.arrayIDS = arrayIDS;

        try{
            interfaz = (interfazUsuarios) context;
        }catch(ClassCastException ex){
            Log.d("MIO","interfaz mal"+ex.getMessage());
            //.. should log the error or throw and exception
        }
    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_usuarios,viewGroup, false);

        return new UsuariosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder usuariosViewHolder, final int i) {
        final int id = i;

        usuariosViewHolder.nombre.setText(arrayUsers.get(i).getNombre());
        usuariosViewHolder.id.setText(arrayIDS.get(i));

        usuariosViewHolder.seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz.userSeguido(arrayIDS.get(i), arrayUsers.get(i).getNombre());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayUsers.size();
    }

    public interface interfazUsuarios{

        void userSeguido(String id, String nombre);
    }
}
