package com.example.parcialmovil2.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parcialmovil2.R;
import com.example.parcialmovil2.data.musica;
import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listAdapter extends RecyclerView.Adapter<listAdapter.MusicViewHolder> {
    private int positionActual;
    private Context contextActual;
    private LayoutInflater mInflater;
    private final LinkedList<musica> listMusica ;

    public listAdapter(Context context, LinkedList<musica> lista) {
        contextActual = context;
        mInflater = LayoutInflater.from(context);
        this.listMusica = lista;
    }

    @NonNull
    @Override
    public listAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.itemlist, parent, false);
        return new MusicViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.MusicViewHolder holder,int position) {
        musica m = this.listMusica.get(position);
        holder.Name.setText(m.artista + " - " + m.cancion);
    }

    @Override
    public int getItemCount() {
        return listMusica.size();
    }


    class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView Name;
        private listAdapter adapter;

        public MusicViewHolder(@NonNull View itemView, listAdapter adapter) {
            super(itemView);
            Name = itemView.findViewById(R.id.title);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {

        }
    }
}


