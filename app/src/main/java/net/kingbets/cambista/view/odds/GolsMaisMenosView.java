package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.GolsMaisMenos;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class GolsMaisMenosView extends BaseOddsView {



    private GolsMaisMenos golsMaisMenos;

    private WidgetOdd wgtMais_05;
    private WidgetOdd wgtMenos_05;
    private WidgetOdd wgtMais_15;
    private WidgetOdd wgtMenos_15;
    private WidgetOdd wgtMais_25;
    private WidgetOdd wgtMenos_25;
    private WidgetOdd wgtMais_35;
    private WidgetOdd wgtMenos_35;
    private WidgetOdd wgtMais_45;
    private WidgetOdd wgtMenos_45;
    private WidgetOdd wgtMais_55;
    private WidgetOdd wgtMenos_55;

    private TextView txvOddMais_05;
    private TextView txvOddMenos_05;
    private TextView txvOddMais_15;
    private TextView txvOddMenos_15;
    private TextView txvOddMais_25;
    private TextView txvOddMenos_25;
    private TextView txvOddMais_35;
    private TextView txvOddMenos_35;
    private TextView txvOddMais_45;
    private TextView txvOddMenos_45;
    private TextView txvOddMais_55;
    private TextView txvOddMenos_55;



    public GolsMaisMenosView(Context context, GolsMaisMenos golsMaisMenos) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_gols_mais_menos, null, false));
        setContext(context);
        this.golsMaisMenos = golsMaisMenos;
    }



    @Override
    public GolsMaisMenosView create(BaseFragment parent) {

        Bet mais05  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 0.5 Gols").sentenca("+;0.5").cotacao(golsMaisMenos.mais_05);
        Bet menos05 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 0.5 Gols").sentenca("-;0.5").cotacao(golsMaisMenos.menos_05);
        Bet mais15  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 1.5 Gols").sentenca("+;1.5").cotacao(golsMaisMenos.mais_15);
        Bet menos15 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 1.5 Gols").sentenca("-;1.5").cotacao(golsMaisMenos.menos_15);
        Bet mais25  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 2.5 Gols").sentenca("+;2.5").cotacao(golsMaisMenos.mais_25);
        Bet menos25 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 2.5 Gols").sentenca("-;2.5").cotacao(golsMaisMenos.menos_25);
        Bet mais35  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 3.5 Gols").sentenca("+;3.5").cotacao(golsMaisMenos.mais_35);
        Bet menos35 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 3.5 Gols").sentenca("-;3.5").cotacao(golsMaisMenos.menos_35);
        Bet mais45  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 4.5 Gols").sentenca("+;4.5").cotacao(golsMaisMenos.mais_45);
        Bet menos45 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 4.5 Gols").sentenca("-;4.5").cotacao(golsMaisMenos.menos_45);
        Bet mais55  = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Mais de 5.5 Gols").sentenca("+;5.5").cotacao(golsMaisMenos.mais_55);
        Bet menos55 = new Bet(GolsMaisMenos.TIPO).odd(golsMaisMenos.id).partida(golsMaisMenos.partida).titulo("Menos de 5.5 Gols").sentenca("-;5.5").cotacao(golsMaisMenos.menos_55);

        wgtMais_05  = new WidgetOdd(mais05, getRootView().findViewById(R.id.layout_odd_mais_05), parent);
        wgtMenos_05 = new WidgetOdd(menos05, getRootView().findViewById(R.id.layout_odd_menos_05), parent);
        wgtMais_15  = new WidgetOdd(mais15, getRootView().findViewById(R.id.layout_odd_mais_15), parent);
        wgtMenos_15 = new WidgetOdd(menos15, getRootView().findViewById(R.id.layout_odd_menos_15), parent);
        wgtMais_25  = new WidgetOdd(mais25, getRootView().findViewById(R.id.layout_odd_mais_25), parent);
        wgtMenos_25 = new WidgetOdd(menos25, getRootView().findViewById(R.id.layout_odd_menos_25), parent);
        wgtMais_35  = new WidgetOdd(mais35, getRootView().findViewById(R.id.layout_odd_mais_35), parent);
        wgtMenos_35 = new WidgetOdd(menos35, getRootView().findViewById(R.id.layout_odd_menos_35), parent);
        wgtMais_45  = new WidgetOdd(mais45, getRootView().findViewById(R.id.layout_odd_mais_45), parent);
        wgtMenos_45 = new WidgetOdd(menos45, getRootView().findViewById(R.id.layout_odd_menos_45), parent);
        wgtMais_55  = new WidgetOdd(mais55, getRootView().findViewById(R.id.layout_odd_mais_55), parent);
        wgtMenos_55 = new WidgetOdd(menos55, getRootView().findViewById(R.id.layout_odd_menos_55), parent);

        txvOddMais_05 = getRootView().findViewById(R.id.txv_odd_mais_05);
        txvOddMenos_05 = getRootView().findViewById(R.id.txv_odd_menos_05);
        txvOddMais_15 = getRootView().findViewById(R.id.txv_odd_mais_15);
        txvOddMenos_15 = getRootView().findViewById(R.id.txv_odd_menos_15);
        txvOddMais_25 = getRootView().findViewById(R.id.txv_odd_mais_25);
        txvOddMenos_25 = getRootView().findViewById(R.id.txv_odd_menos_25);
        txvOddMais_35 = getRootView().findViewById(R.id.txv_odd_mais_35);
        txvOddMenos_35 = getRootView().findViewById(R.id.txv_odd_menos_35);
        txvOddMais_45 = getRootView().findViewById(R.id.txv_odd_mais_45);
        txvOddMenos_45 = getRootView().findViewById(R.id.txv_odd_menos_45);
        txvOddMais_55 = getRootView().findViewById(R.id.txv_odd_mais_55);
        txvOddMenos_55 = getRootView().findViewById(R.id.txv_odd_menos_55);

        return this;
    }


    @Override
    public GolsMaisMenosView build() {

        txvOddMais_05.setText( wgtMais_05.getTextOdd() );
        txvOddMenos_05.setText( wgtMenos_05.getTextOdd() );
        txvOddMais_15.setText( wgtMais_15.getTextOdd() );
        txvOddMenos_15.setText( wgtMenos_15.getTextOdd() );
        txvOddMais_25.setText( wgtMais_25.getTextOdd() );
        txvOddMenos_25.setText( wgtMenos_25.getTextOdd() );
        txvOddMais_35.setText( wgtMais_35.getTextOdd() );
        txvOddMenos_35.setText( wgtMenos_35.getTextOdd() );
        txvOddMais_45.setText( wgtMais_45.getTextOdd() );
        txvOddMenos_45.setText( wgtMenos_45.getTextOdd() );
        txvOddMais_55.setText( wgtMais_55.getTextOdd() );
        txvOddMenos_55.setText( wgtMenos_55.getTextOdd() );

        wgtMais_05.refresh();
        wgtMenos_05.refresh();
        wgtMais_15.refresh();
        wgtMenos_15.refresh();
        wgtMais_25.refresh();
        wgtMenos_25.refresh();
        wgtMais_35.refresh();
        wgtMenos_35.refresh();
        wgtMais_45.refresh();
        wgtMenos_45.refresh();
        wgtMais_55.refresh();
        wgtMenos_55.refresh();

        return this;
    }
}
