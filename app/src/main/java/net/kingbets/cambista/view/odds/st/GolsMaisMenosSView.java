package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.segundas.GolsMaisMenosS;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class GolsMaisMenosSView extends BaseOddsView {



    private GolsMaisMenosS golsMaisMenosS;

    private WidgetOdd wgtMais_05;
    private WidgetOdd wgtMenos_05;
    private WidgetOdd wgtMais_15;
    private WidgetOdd wgtMenos_15;
    private WidgetOdd wgtMais_25;
    private WidgetOdd wgtMenos_25;
    private WidgetOdd wgtMais_35;
    private WidgetOdd wgtMenos_35;

    private TextView txvOddMais_05;
    private TextView txvOddMenos_05;
    private TextView txvOddMais_15;
    private TextView txvOddMenos_15;
    private TextView txvOddMais_25;
    private TextView txvOddMenos_25;
    private TextView txvOddMais_35;
    private TextView txvOddMenos_35;



    public GolsMaisMenosSView(Context context, GolsMaisMenosS golsMaisMenosS) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_gols_mais_menos_st, null, false));
        setContext(context);
        this.golsMaisMenosS = golsMaisMenosS;
    }



    @Override
    public GolsMaisMenosSView create(BaseFragment parent) {

        Bet mais05  = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 0.5 Gols").sentenca("+;0.5").cotacao(golsMaisMenosS.mais_05);
        Bet menos05 = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 0.5 Gols").sentenca("-;0.5").cotacao(golsMaisMenosS.menos_05);
        Bet mais15  = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 1.5 Gols").sentenca("+;1.5").cotacao(golsMaisMenosS.mais_15);
        Bet menos15 = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 1.5 Gols").sentenca("-;1.5").cotacao(golsMaisMenosS.menos_15);
        Bet mais25  = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 2.5 Gols").sentenca("+;2.5").cotacao(golsMaisMenosS.mais_25);
        Bet menos25 = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 2.5 Gols").sentenca("-;2.5").cotacao(golsMaisMenosS.menos_25);
        Bet mais35  = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 3.5 Gols").sentenca("+;3.5").cotacao(golsMaisMenosS.mais_35);
        Bet menos35 = new Bet(GolsMaisMenosS.TIPO).odd(golsMaisMenosS.id).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 3.5 Gols").sentenca("-;3.5").cotacao(golsMaisMenosS.menos_35);

        wgtMais_05  = new WidgetOdd(mais05, getRootView().findViewById(R.id.layout_odd_mais_05_st), parent);
        wgtMenos_05 = new WidgetOdd(menos05, getRootView().findViewById(R.id.layout_odd_menos_05_st), parent);
        wgtMais_15  = new WidgetOdd(mais15, getRootView().findViewById(R.id.layout_odd_mais_15_st), parent);
        wgtMenos_15 = new WidgetOdd(menos15, getRootView().findViewById(R.id.layout_odd_menos_15_st), parent);
        wgtMais_25  = new WidgetOdd(mais25, getRootView().findViewById(R.id.layout_odd_mais_25_st), parent);
        wgtMenos_25 = new WidgetOdd(menos25, getRootView().findViewById(R.id.layout_odd_menos_25_st), parent);
        wgtMais_35  = new WidgetOdd(mais35, getRootView().findViewById(R.id.layout_odd_mais_35_st), parent);
        wgtMenos_35 = new WidgetOdd(menos35, getRootView().findViewById(R.id.layout_odd_menos_35_st), parent);

        txvOddMais_05   = getRootView().findViewById(R.id.txv_odd_mais_05);
        txvOddMenos_05  = getRootView().findViewById(R.id.txv_odd_menos_05);
        txvOddMais_15   = getRootView().findViewById(R.id.txv_odd_mais_15);
        txvOddMenos_15  = getRootView().findViewById(R.id.txv_odd_menos_15);
        txvOddMais_25   = getRootView().findViewById(R.id.txv_odd_mais_25);
        txvOddMenos_25  = getRootView().findViewById(R.id.txv_odd_menos_25);
        txvOddMais_35   = getRootView().findViewById(R.id.txv_odd_mais_35);
        txvOddMenos_35  = getRootView().findViewById(R.id.txv_odd_menos_35);

        return this;
    }


    @Override
    public GolsMaisMenosSView build() {

        txvOddMais_05.setText( wgtMais_05.getTextOdd() );
        txvOddMenos_05.setText( wgtMenos_05.getTextOdd() );
        txvOddMais_15.setText( wgtMais_15.getTextOdd() );
        txvOddMenos_15.setText( wgtMenos_15.getTextOdd() );
        txvOddMais_25.setText( wgtMais_25.getTextOdd() );
        txvOddMenos_25.setText( wgtMenos_25.getTextOdd() );
        txvOddMais_35.setText( wgtMais_35.getTextOdd() );
        txvOddMenos_35.setText( wgtMenos_35.getTextOdd() );

        wgtMais_05.refresh();
        wgtMenos_05.refresh();
        wgtMais_15.refresh();
        wgtMenos_15.refresh();
        wgtMais_25.refresh();
        wgtMenos_25.refresh();
        wgtMais_35.refresh();
        wgtMenos_35.refresh();

        return this;
    }
}
