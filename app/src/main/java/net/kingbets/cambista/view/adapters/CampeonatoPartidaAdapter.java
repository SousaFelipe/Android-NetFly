package net.kingbets.cambista.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.futebol.CampeonatoPartidas;
import net.kingbets.cambista.utils.Img;
import net.kingbets.cambista.view.fragments.PartidasFragment;

import java.util.List;


public class CampeonatoPartidaAdapter extends RecyclerView.Adapter<CampeonatoPartidaAdapter.ViewHolder> {



    private Context context;
    private PartidasFragment parent;
    private List<CampeonatoPartidas> campeonatoPartidas;



    public CampeonatoPartidaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CampeonatoPartidaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campeonato_partidas, parent, false);
        return new CampeonatoPartidaAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CampeonatoPartidaAdapter.ViewHolder holder, int position) {
        CampeonatoPartidas campeonatoPartidas = this.campeonatoPartidas.get( position );


        if (campeonatoPartidas.partidas.size() > 0) {

            holder.adapter = new PartidaAdapter();
            holder.imgBandeira.setImageResource(Img.getSquareResourceId(context, campeonatoPartidas.getFlag()) );
            holder.txvTitulo.setText(campeonatoPartidas.titulo);
            holder.txvTotalPartidas.setText( String.valueOf( campeonatoPartidas.partidas.size() ) );

            holder.adapter.setParent(parent);
            holder.adapter.setDataList(campeonatoPartidas.partidas);

            holder.recycler.setLayoutManager(new LinearLayoutManager( context ));
            holder.recycler.setAdapter(holder.adapter);
            holder.recycler.scrollTo(0, 0);
        }
        else {
            holder.adapter.clear();
        }
    }


    @Override
    public int getItemCount() {
        return campeonatoPartidas.size();
    }



    public void setDataList(List<CampeonatoPartidas> campeonatos) {
        this.campeonatoPartidas = campeonatos;
    }


    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    public void clear() {
        if (campeonatoPartidas != null) {
            campeonatoPartidas.clear();
            notifyDataSetChanged();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imgBandeira;
        TextView txvTitulo;
        TextView txvTotalPartidas;

        RecyclerView recycler;
        PartidaAdapter adapter;


        ViewHolder(View view) {
            super(view);

            imgBandeira = view.findViewById(R.id.img_bandeira);
            txvTitulo = view.findViewById(R.id.txv_titulo);
            txvTotalPartidas = view.findViewById(R.id.txv_total_partidas);
            recycler = view.findViewById(R.id.recycler_partidas);
        }
    }
}
