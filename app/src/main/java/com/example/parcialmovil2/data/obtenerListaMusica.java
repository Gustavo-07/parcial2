package com.example.parcialmovil2.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
import com.example.parcialmovil2.R;

import androidx.annotation.RequiresPermission;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class obtenerListaMusica extends AsyncTask<Void, Void, Void> {

    public static LinkedList<musica> guardar = new LinkedList<>();
     LinkedList<musica> data = new LinkedList<>();
    LinkedList<musica> listaMusica = new LinkedList<>();
    Activity activity;

    public obtenerListaMusica(Activity activiti, LinkedList<musica> data){
        this.activity = activiti;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.activity.getBaseContext(), "Loading Music...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder sountracks = new StringBuilder();
        try {

            URL url = new URL("https://api.deezer.com/playlist/93489551/tracks");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                sountracks.append(scan.next());
            }

            JSONObject objeto = new JSONObject(sountracks.toString());
            JSONArray listaSoundTracks = objeto.getJSONArray("data");
            int i=listaSoundTracks.length();
            for (int j = 0; j < i ; j++) {
                listaMusica.add(mapeoMusicaApi(listaSoundTracks, j));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (guardar.size() > 0){
            for(musica str: guardar)
            {
                listaMusica.add(str);
            }
        }


        RecyclerView list;
        listAdapter adapter = new listAdapter(this.activity.getBaseContext(), listaMusica);
        list = activity.findViewById(R.id.listMusic);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this.activity.getBaseContext()));

    }

    public musica mapeoMusicaApi(JSONArray listaSoundTracks, int i) throws JSONException {

        JSONObject track = listaSoundTracks.getJSONObject(i);
        musica datos = new musica(track.getInt("id"));

        JSONObject artista = track.getJSONObject("artist");
        datos.artista = artista.getString("name");

        JSONObject album = track.getJSONObject("album");
        datos.album = album.getString("title");

        datos.cancion = track.getString("title");
        datos.duarcion = track.getInt("duration");

        return datos;

    }

}
