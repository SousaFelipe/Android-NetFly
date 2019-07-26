package net.kingbets.cambista.view.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.futebol.Partida;
import net.kingbets.cambista.model.remote.odds.principais.Resultado;
import net.kingbets.cambista.utils.Format;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.widgets.MaisOddsWidget;
import net.kingbets.cambista.view.widgets.Widget;

import java.util.List;


public class PartidaAdapter extends RecyclerView.Adapter<PartidaAdapter.ViewHolder> {



    private List<Partida> partidas;
    private PartidasFragment parent;


    @NonNull
    @Override
    public PartidaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partida, parent, false);
        return new PartidaAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PartidaAdapter.ViewHolder holder, int position) {

        final Partida partida = partidas.get( position );

        holder.txvHora.setText(Format.Time.compaact(partida.inicio));
        holder.txvCasa.setText(partida.casa);
        holder.txvFora.setText(partida.fora);

        criaCasaWidgets(holder, position);
        criaEmpateWidgets(holder, position);
        criaForaWidgets(holder, position);

        holder.wgtMaisOdds.setBackgroundResource(R.drawable.bg_odd_red_right);
        holder.wgtMaisOdds.setPartida(partida);
        holder.wgtMaisOdds.verificaSelecao();
        holder.wgtMaisOdds.setParent(parent);
    }


    @Override
    public int getItemCount() {
        return partidas.size();
    }



    void setDataList(List<Partida> campeonatos) {
        this.partidas = campeonatos;
    }



    void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    private void criaCasaWidgets(ViewHolder holder, int position) {
        Partida partida = partidas.get( position );

        holder.wgtCasa.setAposta(new Aposta(Resultado.TIPO).withPartida(partida));
        holder.wgtCasa.setBackgroundResource(R.drawable.bg_odd_red_left);
        holder.wgtCasa.setCotacao(partida.resultado.casa);
        holder.wgtCasa.setTitulo("Casa");
        holder.wgtCasa.mostraCotacao();
        holder.wgtCasa.verificaSelecao();
    }

    private void criaEmpateWidgets(ViewHolder holder, int position) {
        Partida partida = partidas.get( position );

        holder.wgtEmpate.setAposta(new Aposta(Resultado.TIPO).withPartida(partida));
        holder.wgtEmpate.setBackgroundResource(R.drawable.bg_odd_red_mid);
        holder.wgtEmpate.setCotacao(partida.resultado.empate);
        holder.wgtEmpate.setTitulo("Empate");
        holder.wgtEmpate.mostraCotacao();
        holder.wgtEmpate.verificaSelecao();
    }

    private void criaForaWidgets(ViewHolder holder, int position) {
        Partida partida = partidas.get( position );

        holder.wgtFora.setAposta(new Aposta(Resultado.TIPO).withPartida(partida));
        holder.wgtFora.setBackgroundResource(R.drawable.bg_odd_red_mid);
        holder.wgtFora.setCotacao(partida.resultado.fora);
        holder.wgtFora.setTitulo("Fora");
        holder.wgtFora.mostraCotacao();
        holder.wgtFora.verificaSelecao();
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

        Widget wgtCasa;
        Widget wgtEmpate;
        Widget wgtFora;
        MaisOddsWidget wgtMaisOdds;


        ViewHolder(View view) {
            super(view);

            txvHora = view.findViewById(R.id.txv_hora);
            txvCasa = view.findViewById(R.id.txv_casa);
            txvFora = view.findViewById(R.id.txv_fora);

            wgtCasa = new Widget(view.findViewById(R.id.layout_odd_casa));
            wgtEmpate = new Widget(view.findViewById(R.id.layout_odd_empate));
            wgtFora = new Widget(view.findViewById(R.id.layout_odd_fora));
            wgtMaisOdds = new MaisOddsWidget(view.findViewById(R.id.layout_mais_odds));
        }
    }
}
