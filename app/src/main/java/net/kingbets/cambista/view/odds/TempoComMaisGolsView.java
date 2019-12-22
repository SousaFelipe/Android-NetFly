package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.TempoComMaisGols;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class TempoComMaisGolsView extends BaseOddsView {



    private TempoComMaisGols tempoComMaisGols;

    private WidgetOdd wgtPrimeiro;
    private WidgetOdd wgtSegundo;
    private WidgetOdd wgtIguais;

    private TextView txvOddPrimeiro;
    private TextView txvOddSegundo;
    private TextView txvOddIguais;



    public TempoComMaisGolsView(Context context, TempoComMaisGols tempoComMaisGols) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_tempo_com_mais_gols, null, false));
        setContext(context);
        this.tempoComMaisGols = tempoComMaisGols;
    }


    @Override
    public TempoComMaisGolsView create(BaseFragment parent) {

        Bet primeiro = new Bet(TempoComMaisGols.TIPO).odd(tempoComMaisGols.id).partida(tempoComMaisGols.partida).titulo("Tempo com Mais Gols: 1° Tempo").sentenca("P").cotacao(tempoComMaisGols.primeiro);
        Bet segundo = new Bet(TempoComMaisGols.TIPO).odd(tempoComMaisGols.id).partida(tempoComMaisGols.partida).titulo("Tempo com Mais Gols: 2° Tempo").sentenca("S").cotacao(tempoComMaisGols.segundo);
        Bet iguais = new Bet(TempoComMaisGols.TIPO).odd(tempoComMaisGols.id).partida(tempoComMaisGols.partida).titulo("Tempo com Mais Gols: Iguais").sentenca("I").cotacao(tempoComMaisGols.iguais);

        wgtPrimeiro = new WidgetOdd(primeiro, getRootView().findViewById(R.id.layout_odd_primeiro), parent);
        wgtSegundo = new WidgetOdd(segundo, getRootView().findViewById(R.id.layout_odd_segundo), parent);
        wgtIguais = new WidgetOdd(iguais, getRootView().findViewById(R.id.layout_odd_iguais), parent);

        txvOddPrimeiro = getRootView().findViewById(R.id.txv_odd_primeiro);
        txvOddSegundo = getRootView().findViewById(R.id.txv_odd_segundo);
        txvOddIguais = getRootView().findViewById(R.id.txv_odd_iguais);

        return this;
    }


    @Override
    public TempoComMaisGolsView build() {

        txvOddPrimeiro.setText( wgtPrimeiro.getTextOdd() );
        txvOddSegundo.setText( wgtSegundo.getTextOdd() );
        txvOddIguais.setText( wgtIguais.getTextOdd() );

        wgtPrimeiro.refresh();
        wgtSegundo.refresh();
        wgtIguais.refresh();

        return this;
    }
}
