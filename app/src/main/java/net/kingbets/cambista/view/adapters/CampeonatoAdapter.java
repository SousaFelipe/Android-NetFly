package net.kingbets.cambista.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.futebol.Campeonato;
import net.kingbets.cambista.utils.Img;

import java.util.ArrayList;
import java.util.List;


public class CampeonatoAdapter extends RecyclerView.Adapter<CampeonatoAdapter.ViewHolder> {



    private Context context;
    private List<Campeonato> campeonatos;



    public CampeonatoAdapter(List<Campeonato> campeonatos) {
        this.campeonatos = campeonatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campeonato, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Campeonato campeonato = campeonatos.get(position);
        String titulo = campeonato.titulo + " Série " + campeonato.serie;

        holder.imgBandeira.setImageDrawable(Img.getDrawable(context, campeonato.bandeira));
        holder.txvTitulo.setText(titulo);
        holder.txvFim.setText(campeonato.fim);
        holder.txvPartidas.setText(campeonato.partidas);
    }


    @Override
    public int getItemCount() {
        return campeonatos.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgBandeira;
        TextView txvTitulo;
        TextView txvFim;
        TextView txvPartidas;


        ViewHolder(View view) {
            super(view);

            imgBandeira = view.findViewById(R.id.img_bandeira);
            txvTitulo = view.findViewById(R.id.txv_titulo);
            txvFim = view.findViewById(R.id.txv_fim);
            txvPartidas = view.findViewById(R.id.txv_partidas);
        }
    }
}