package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.DuplaChance;
import net.kingbets.cambista.view.widgets.Widget;


public class DuplaChanceView extends BaseOddsView {



    private DuplaChance duplaChance;


    private Widget wgtCasaEmpate;
    private Widget wgtForaEmpate;
    private Widget wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceView(Context context, DuplaChance duplaChance) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance, null, false) );

        setContext(context);
        setAposta(new Aposta(DuplaChance.TIPO).partida(duplaChance.partida));

        this.duplaChance = duplaChance;
    }


    @Override
    public DuplaChanceView create() {

        Aposta casaE = new Aposta(DuplaChance.TIPO).partida(duplaChance.partida).titulo("Casa ou Empate").sentenca("C;E").cotacao(duplaChance.casaEmpate);
        Aposta foraE = new Aposta(DuplaChance.TIPO).partida(duplaChance.partida).titulo("Fora ou Empate").sentenca("F;E").cotacao(duplaChance.foraEmpate);
        Aposta casaF = new Aposta(DuplaChance.TIPO).partida(duplaChance.partida).titulo("Casa ou Fora").sentenca("C;F").cotacao(duplaChance.casaFora);

        wgtCasaEmpate = new Widget(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate));
        wgtForaEmpate = new Widget(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate));
        wgtCasaFora = new Widget(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora));

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceView build() {
        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvOddCasaFora.setText( wgtCasaFora.getTextCotacao() );
        return this;
    }



    public DuplaChanceView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
