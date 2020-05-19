package com.example.parcialmovil2.data;

public class musica {

    public int id;
    public String cancion;
    public String artista;
    public String album;
    public int duarcion;


    public musica (){

    }

    public musica(int id){
        this.id =id;
    }
    public musica (int id, String cancion, String artista, String album, int duarcion) {
        this.id = id;
        this.cancion = cancion;
        this.artista = artista;
        this.album = album;
        this.duarcion = duarcion;
    }


}
