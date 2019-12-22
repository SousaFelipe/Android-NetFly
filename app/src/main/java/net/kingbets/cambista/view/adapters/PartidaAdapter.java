package net.kingbets.cambista.view.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.futebol.Partida;
import net.kingbets.cambista.http.models.odds.principais.Resultado;
import net.kingbets.cambista.utils.Format;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.widgets.WidgetMaisOdds;
import net.kingbets.cambista.view.widgets.WidgetOdd;

import java.util.ArrayList;
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

        Partida partida = partidas.get( position );

        holder.txvHora.setText(Format.Time.compaact(partida.inicio));
        holder.txvCasa.setText(partida.casa);
        holder.txvFora.setText(partida.fora);

        criaCasaWidgets(holder, partida);
        criaEmpateWidgets(holder, partida);
        criaForaWidgets(holder, partida);
        criaWidgetMaisOdds(holder, partida);
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



    private void criaCasaWidgets(ViewHolder holder, Partida partida) {

        Bet bet = new Bet(Resultado.TIPO).partida(partida).titulo("Casa").sentenca("C").cotacao(partida.probabilidades.casa);
        bet.odd = partida.probabilidades.id;

        holder.mainOdds.get(0).setBet(bet);
        holder.mainOdds.get(0).setParent(parent);
        holder.mainOdds.get(0).setSelectedResource(R.drawable.bg_odd_red_left);
        holder.mainOdds.get(0).setRequestResource(R.drawable.bg_odd_gray_left);
        holder.mainOdds.get(0).refresh();
    }



    private void criaEmpateWidgets(ViewHolder holder, Partida partida) {

        Bet bet = new Bet(Resultado.TIPO).partida(partida).titulo("Empate").sentenca("E").cotacao(partida.probabilidades.empate);
        bet.odd = partida.probabilidades.id;

        holder.mainOdds.get(1).setBet(bet);
        holder.mainOdds.get(1).setParent(parent);
        holder.mainOdds.get(1).setSelectedResource(R.drawable.bg_odd_red_mid);
        holder.mainOdds.get(1).setRequestResource(R.drawable.bg_odd_gray_mid);
        holder.mainOdds.get(1).refresh();
    }



    private void criaForaWidgets(ViewHolder holder, Partida partida) {

        Bet bet = new Bet(Resultado.TIPO).partida(partida).titulo("Fora").sentenca("F").cotacao(partida.probabilidades.fora);
        bet.odd = partida.probabilidades.id;

        holder.mainOdds.get(2).setBet(bet);
        holder.mainOdds.get(2).setParent(parent);
        holder.mainOdds.get(2).setSelectedResource(R.drawable.bg_odd_red_mid);
        holder.mainOdds.get(2).setRequestResource(R.drawable.bg_odd_gray_mid);
        holder.mainOdds.get(2).refresh();
    }



    private void criaWidgetMaisOdds(ViewHolder holder, Partida partida) {
        holder.wgtMaisOdds.setBackgroundResource(R.drawable.bg_odd_red_right);
        holder.wgtMaisOdds.setPartida(partida);
        holder.wgtMaisOdds.setParent(parent);
        holder.wgtMaisOdds.refresh(holder.mainOdds);
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

        List<WidgetOdd> mainOdds = new ArrayList<>(3);

        WidgetMaisOdds wgtMaisOdds;


        ViewHolder(View view) {
            super(view);

            txvHora = view.findViewById(R.id.txv_hora);
            txvCasa = view.findViewById(R.id.txv_casa);
            txvFora = view.findViewById(R.id.txv_fora);

            mainOdds.add(new WidgetOdd(view.findViewById(R.id.layout_odd_casa)));
            mainOdds.add(new WidgetOdd(view.findViewById(R.id.layout_odd_empate)));
            mainOdds.add(new WidgetOdd(view.findViewById(R.id.layout_odd_fora)));

            wgtMaisOdds = new WidgetMaisOdds(view.findViewById(R.id.layout_mais_odds));
        }
    }
}
