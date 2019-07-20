package net.kingbets.cambista.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.futebol.Partida;
import net.kingbets.cambista.utils.Format;

import java.util.List;
import java.util.Locale;


public class PartidaAdapter extends RecyclerView.Adapter<PartidaAdapter.ViewHolder> {



    private Context context;
    private List<Partida> partidas;



    PartidaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PartidaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partida, parent, false);
        return new PartidaAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PartidaAdapter.ViewHolder holder, int position) {

        Partida partida = partidas.get( position );

        holder.txvHora.setText(Format.Time.compaact(partida.inicio));
        holder.txvCasa.setText(partida.casa);
        holder.txvFora.setText(partida.fora);

        holder.txvCasaOdd.setText( String.format(Locale.getDefault(), "%.2f", partida.resultado.casa) );
        holder.txvEmpateOdd.setText( String.format(Locale.getDefault(), "%.2f", partida.resultado.empate) );
        holder.txvForaOdd.setText( String.format(Locale.getDefault(), "%.2f", partida.resultado.fora) );
    }


    @Override
    public int getItemCount() {
        return partidas.size();
    }



    void setDataList(List<Partida> campeonatos) {
        this.partidas = campeonatos;
    }



    void clear() {
        if (partidas != null) {
            partidas.clear();
            notifyDataSetChanged();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {



        TextView txvHora;
        TextView txvCasa;
        TextView txvFora;

        LinearLayout btnOddCasa;
        LinearLayout btnOddEmpate;
        LinearLayout btnOddFora;
        FrameLayout btnOddMais;

        TextView txvCasaOdd;
        TextView txvEmpateOdd;
        TextView txvForaOdd;



        ViewHolder(View view) {
            super(view);

            txvHora = view.findViewById(R.id.txv_hora);
            txvCasa = view.findViewById(R.id.txv_casa);
            txvFora = view.findViewById(R.id.txv_fora);

            btnOddCasa = view.findViewById(R.id.btn_odd_casa);
            btnOddEmpate = view.findViewById(R.id.btn_odd_empate);
            btnOddFora = view.findViewById(R.id.btn_odd_fora);
            btnOddMais = view.findViewById(R.id.btn_odd_mais);

            txvCasaOdd = view.findViewById(R.id.txv_casa_odd);
            txvEmpateOdd = view.findViewById(R.id.txv_empate_odd);
            txvForaOdd = view.findViewById(R.id.txv_fora_odd);
        }
    }
}
