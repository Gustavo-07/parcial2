package com.example.parcialmovil2.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

import com.example.parcialmovil2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class buscarMusica extends AsyncTask<Void, Void, Void > {

    String buscarMusica = "";
    Activity contexto;
    musica music = null;

    public buscarMusica(String buscarMusica, Activity contexto){
        this.buscarMusica = buscarMusica;
        this.contexto = contexto;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder musica = new StringBuilder();
        try {

            URL url = new URL("https://api.deezer.com/search?q="+this.buscarMusica);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);

            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                musica.append(scan.next());
            }

            JSONObject objeto = new JSONObject(musica.toString());
            JSONArray cancion = objeto.getJSONArray("data");

            mapeoMusicaApi(cancion, 0);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    public musica mapeoMusicaApi(JSONArray cancion, int i) throws JSONException {

        JSONObject track = cancion.getJSONObject(0);
        music = new musica(track.getInt("id"));
        JSONObject artista = track.getJSONObject("artist");
        music.artista = artista.getString("name");
        JSONObject album = track.getJSONObject("album");
        music.album = album.getString("title");
        music.cancion = track.getString("title");
        music.duarcion = track.getInt("duration");

        return music;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        EditText cancion = (EditText) contexto.findViewById(R.id.cancion);
        EditText artista = (EditText) contexto.findViewById(R.id.artista);
        EditText album = (EditText) contexto.findViewById(R.id.album);
        EditText duracion = (EditText) contexto.findViewById(R.id.duracion);

        cancion.setText(music.cancion);
        artista.setText(music.artista);
        album.setText(music.album);
        duracion.setText(String.valueOf(music.duarcion));
    }
}
