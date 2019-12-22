package net.kingbets.cambista.view.adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.futebol.Campeonato;
import net.kingbets.cambista.utils.Img;

import java.util.List;


public class CampeonatoAdapter extends RecyclerView.Adapter<CampeonatoAdapter.ViewHolder> {



    private Context context;
    private List<Campeonato> campeonatos;



    public CampeonatoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campeonato, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Campeonato campeonato = campeonatos.get(position);
        holder.imgBandeira.setImageResource( Img.getResourceId(context, campeonato.getRoundFlag()) );
        holder.txvTitulo.setText(campeonato.titulo);
        holder.setTotalPartidas(campeonato.partidas);
    }


    @Override
    public int getItemCount() {
        return campeonatos.size();
    }



    public void setDataList(List<Campeonato> campeonatos) {
        this.campeonatos = campeonatos;
    }



    public void clear() {
        if (campeonatos != null) {
            campeonatos.clear();
            notifyDataSetChanged();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgBandeira;
        TextView txvTitulo;
        TextView txvPartidas;
        FrameLayout bgTotalPartidas;


        ViewHolder(View view) {
            super(view);

            imgBandeira = view.findViewById(R.id.img_bandeira);
            txvTitulo = view.findViewById(R.id.txv_titulo);
            txvPartidas = view.findViewById(R.id.txv_partidas);
            bgTotalPartidas =view.findViewById(R.id.bg_total_partidas);
        }



        void setTotalPartidas(int partidas) {

            txvPartidas.setText(
                    partidas > 0 ? String.valueOf(partidas) : "0"
            );

            txvPartidas.setTextColor(Color.parseColor(
                    partidas > 0 ? "#ECEFF1" : "#546E7A"
            ));

            bgTotalPartidas.setBackgroundResource(
                    (partidas > 0) ? R.drawable.bg_badge_dark : R.drawable.bg_badge_light
            );
        }
    }
}
