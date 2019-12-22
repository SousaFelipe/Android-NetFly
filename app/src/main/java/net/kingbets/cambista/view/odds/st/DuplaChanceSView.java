package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.segundas.DuplaChanceS;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class DuplaChanceSView extends BaseOddsView {



    private DuplaChanceS duplaChanceS;


    private WidgetOdd wgtCasaEmpate;
    private WidgetOdd wgtForaEmpate;
    private WidgetOdd wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceSView(Context context, DuplaChanceS duplaChanceS) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance_st, null, false) );
        setContext(context);
        this.duplaChanceS = duplaChanceS;
    }


    @Override
    public DuplaChanceSView create(BaseFragment parent) {

        Bet casaE = new Bet(DuplaChanceS.TIPO).odd(duplaChanceS.id).partida(duplaChanceS.partida).titulo("2° Tempo - Casa ou Empate").sentenca("C;E").cotacao(duplaChanceS.casaEmpate);
        Bet foraE = new Bet(DuplaChanceS.TIPO).odd(duplaChanceS.id).partida(duplaChanceS.partida).titulo("2° Tempo - Fora ou Empate").sentenca("F;E").cotacao(duplaChanceS.foraEmpate);
        Bet casaF = new Bet(DuplaChanceS.TIPO).odd(duplaChanceS.id).partida(duplaChanceS.partida).titulo("2° Tempo - Casa ou Fora").sentenca("C;F").cotacao(duplaChanceS.casaFora);

        wgtCasaEmpate = new WidgetOdd(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate_st), parent);
        wgtForaEmpate = new WidgetOdd(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate_st), parent);
        wgtCasaFora   = new WidgetOdd(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora_st), parent);

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceSView build() {

        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextOdd() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextOdd() );
        txvOddCasaFora.setText( wgtCasaFora.getTextOdd() );

        wgtCasaEmpate.refresh();
        wgtForaEmpate.refresh();
        wgtCasaFora.refresh();

        return this;
    }



    public DuplaChanceSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
