package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.FaixaDeGols;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class FaixaDeGolsView extends BaseOddsView {



    private FaixaDeGols faixaDeGols;

    private WidgetOdd wgt_01;
    private WidgetOdd wgt_23;
    private WidgetOdd wgt_45;
    private WidgetOdd wgt_M6;

    private TextView txvOdd_01;
    private TextView txvOdd_23;
    private TextView txvOdd_45;
    private TextView txvOdd_M6;



    public FaixaDeGolsView(Context context, FaixaDeGols faixaDeGols) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_faixa_de_gols, null, false));
        setContext(context);
        this.faixaDeGols = faixaDeGols;
    }


    @Override
    public FaixaDeGolsView create(BaseFragment parent) {

        Bet _01 = new Bet(FaixaDeGols.TIPO).odd(faixaDeGols.id).partida(faixaDeGols.partida).titulo("Faixa de Gols: 0 a 1").sentenca("_01").cotacao(faixaDeGols._01);
        Bet _23 = new Bet(FaixaDeGols.TIPO).odd(faixaDeGols.id).partida(faixaDeGols.partida).titulo("Faixa de Gols: 2 a 3").sentenca("_23").cotacao(faixaDeGols._23);
        Bet _45 = new Bet(FaixaDeGols.TIPO).odd(faixaDeGols.id).partida(faixaDeGols.partida).titulo("Faixa de Gols: 4 a 5").sentenca("_45").cotacao(faixaDeGols._45);
        Bet _M6 = new Bet(FaixaDeGols.TIPO).odd(faixaDeGols.id).partida(faixaDeGols.partida).titulo("Faixa de Gols: + de 6").sentenca("_M6").cotacao(faixaDeGols._M6);

        wgt_01 = new WidgetOdd(_01, getRootView().findViewById(R.id.layout_odd_01), parent);
        wgt_23 = new WidgetOdd(_23, getRootView().findViewById(R.id.layout_odd_23), parent);
        wgt_45 = new WidgetOdd(_45, getRootView().findViewById(R.id.layout_odd_45), parent);
        wgt_M6 = new WidgetOdd(_M6, getRootView().findViewById(R.id.layout_odd_M6), parent);

        txvOdd_01 = getRootView().findViewById(R.id.txv_odd_01);
        txvOdd_23 = getRootView().findViewById(R.id.txv_odd_23);
        txvOdd_45 = getRootView().findViewById(R.id.txv_odd_45);
        txvOdd_M6 = getRootView().findViewById(R.id.txv_odd_M6);

        return this;
    }


    @Override
    public FaixaDeGolsView build() {

        txvOdd_01.setText( wgt_01.getTextOdd() );
        txvOdd_23.setText( wgt_23.getTextOdd() );
        txvOdd_45.setText( wgt_45.getTextOdd() );
        txvOdd_M6.setText( wgt_M6.getTextOdd() );

        wgt_01.refresh();
        wgt_23.refresh();
        wgt_45.refresh();
        wgt_M6.refresh();

        return this;
    }
}
