package com.example.parcialmovil2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.parcialmovil2.data.listAdapter;
import com.example.parcialmovil2.data.musica;
import com.example.parcialmovil2.data.buscarMusica;
import com.example.parcialmovil2.data.obtenerListaMusica;

import java.util.ArrayList;
import java.util.LinkedList;

public class agregarmusica extends AppCompatActivity {

    String TituloMusica = "";
    EditText musica;
    EditText artista;
    EditText album;
    EditText duracion;
    musica music = new musica();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_musica);
        musica = (EditText) findViewById(R.id.cancion);
        artista = (EditText) findViewById(R.id.artista);
        album = (EditText) findViewById(R.id.album);
        duracion = (EditText) findViewById(R.id.duracion);
        SearchView buscar = (SearchView) findViewById ( R.id.buscar);

        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new buscarMusica(query,agregarmusica.this).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    public void guardarBusqueda(View view) {

        musica music = new musica();
        music.cancion = musica.getText().toString();
        music.artista = artista.getText().toString();
        music.album = album.getText().toString();
        music.duarcion = Integer.parseInt(duracion.getText().toString());

         obtenerListaMusica.guardar.add(music);
        Toast.makeText(this, "musica guardada", Toast.LENGTH_SHORT).show();

    }
}
