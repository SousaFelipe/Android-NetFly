package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.primeiras.DuplaChanceP;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class DuplaChancePView extends BaseOddsView {



    private DuplaChanceP duplaChanceP;


    private Widget wgtCasaEmpate;
    private Widget wgtForaEmpate;
    private Widget wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChancePView(Context context, DuplaChanceP duplaChanceP) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance_pt, null, false) );

        setContext(context);
        setAposta(new Aposta(DuplaChanceP.TIPO).partida(duplaChanceP.partida));

        this.duplaChanceP = duplaChanceP;
    }


    @Override
    public DuplaChancePView create() {

        Aposta casaE = new Aposta(DuplaChanceP.TIPO).partida(duplaChanceP.partida).titulo("1° Tempo - Casa ou Empate").sentenca("C;E").cotacao(duplaChanceP.casaEmpate);
        Aposta foraE = new Aposta(DuplaChanceP.TIPO).partida(duplaChanceP.partida).titulo("1° Tempo - Fora ou Empate").sentenca("F;E").cotacao(duplaChanceP.foraEmpate);
        Aposta casaF = new Aposta(DuplaChanceP.TIPO).partida(duplaChanceP.partida).titulo("1° Tempo - Casa ou Fora").sentenca("C;F").cotacao(duplaChanceP.casaFora);

        wgtCasaEmpate = new Widget(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate_pt));
        wgtForaEmpate = new Widget(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate_pt));
        wgtCasaFora = new Widget(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora_pt));

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChancePView build() {
        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvOddCasaFora.setText( wgtCasaFora.getTextCotacao() );
        return this;
    }



    public DuplaChancePView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
