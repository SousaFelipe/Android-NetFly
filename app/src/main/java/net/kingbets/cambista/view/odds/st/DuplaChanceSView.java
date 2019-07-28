package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.DuplaChanceS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class DuplaChanceSView extends BaseOddsView {



    private DuplaChanceS duplaChanceS;


    private Widget wgtCasaEmpate;
    private Widget wgtForaEmpate;
    private Widget wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceSView(Context context, DuplaChanceS duplaChanceS) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance_st, null, false) );

        setContext(context);
        setAposta(new Aposta(DuplaChanceS.TIPO).partida(duplaChanceS.partida));

        this.duplaChanceS = duplaChanceS;
    }


    @Override
    public DuplaChanceSView create() {

        Aposta casaE = new Aposta(DuplaChanceS.TIPO).partida(duplaChanceS.partida).titulo("2° Tempo - Casa ou Empate").sentenca("C;E").cotacao(duplaChanceS.casaEmpate);
        Aposta foraE = new Aposta(DuplaChanceS.TIPO).partida(duplaChanceS.partida).titulo("2° Tempo - Fora ou Empate").sentenca("F;E").cotacao(duplaChanceS.foraEmpate);
        Aposta casaF = new Aposta(DuplaChanceS.TIPO).partida(duplaChanceS.partida).titulo("2° Tempo - Casa ou Fora").sentenca("C;F").cotacao(duplaChanceS.casaFora);

        wgtCasaEmpate = new Widget(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate_st));
        wgtForaEmpate = new Widget(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate_st));
        wgtCasaFora = new Widget(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora_st));

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceSView build() {
        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvOddCasaFora.setText( wgtCasaFora.getTextCotacao() );
        return this;
    }



    public DuplaChanceSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
