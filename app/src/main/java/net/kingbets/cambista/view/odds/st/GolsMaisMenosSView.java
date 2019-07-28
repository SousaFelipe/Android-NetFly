package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.GolsMaisMenosS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class GolsMaisMenosSView extends BaseOddsView {



    private GolsMaisMenosS golsMaisMenosS;

    private Widget wgtMais_05;
    private Widget wgtMenos_05;
    private Widget wgtMais_15;
    private Widget wgtMenos_15;
    private Widget wgtMais_25;
    private Widget wgtMenos_25;
    private Widget wgtMais_35;
    private Widget wgtMenos_35;

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
        setAposta(new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida));

        this.golsMaisMenosS = golsMaisMenosS;
    }



    @Override
    public GolsMaisMenosSView create() {

        Aposta mais05 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 0.5 Gols").sentenca("+;0.5").cotacao(golsMaisMenosS.mais_05);
        Aposta menos05 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 0.5 Gols").sentenca("-;0.5").cotacao(golsMaisMenosS.menos_05);
        Aposta mais15 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 1.5 Gols").sentenca("+;1.5").cotacao(golsMaisMenosS.mais_15);
        Aposta menos15 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 1.5 Gols").sentenca("-;1.5").cotacao(golsMaisMenosS.menos_15);
        Aposta mais25 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 2.5 Gols").sentenca("+;2.5").cotacao(golsMaisMenosS.mais_25);
        Aposta menos25 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 2.5 Gols").sentenca("-;2.5").cotacao(golsMaisMenosS.menos_25);
        Aposta mais35 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Mais de 3.5 Gols").sentenca("+;3.5").cotacao(golsMaisMenosS.mais_35);
        Aposta menos35 = new Aposta(GolsMaisMenosS.TIPO).partida(golsMaisMenosS.partida).titulo("2° Tempo - Menos de 3.5 Gols").sentenca("-;3.5").cotacao(golsMaisMenosS.menos_35);

        wgtMais_05 = new Widget(mais05, getRootView().findViewById(R.id.layout_odd_mais_05_st));
        wgtMenos_05 = new Widget(menos05, getRootView().findViewById(R.id.layout_odd_menos_05_st));
        wgtMais_15 = new Widget(mais15, getRootView().findViewById(R.id.layout_odd_mais_15_st));
        wgtMenos_15 = new Widget(menos15, getRootView().findViewById(R.id.layout_odd_menos_15_st));
        wgtMais_25 = new Widget(mais25, getRootView().findViewById(R.id.layout_odd_mais_25_st));
        wgtMenos_25 = new Widget(menos25, getRootView().findViewById(R.id.layout_odd_menos_25_st));
        wgtMais_35 = new Widget(mais35, getRootView().findViewById(R.id.layout_odd_mais_35_st));
        wgtMenos_35 = new Widget(menos35, getRootView().findViewById(R.id.layout_odd_menos_35_st));

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

        txvOddMais_05.setText( wgtMais_05.getTextCotacao() );
        txvOddMenos_05.setText( wgtMenos_05.getTextCotacao() );
        txvOddMais_15.setText( wgtMais_15.getTextCotacao() );
        txvOddMenos_15.setText( wgtMenos_15.getTextCotacao() );
        txvOddMais_25.setText( wgtMais_25.getTextCotacao() );
        txvOddMenos_25.setText( wgtMenos_25.getTextCotacao() );
        txvOddMais_35.setText( wgtMais_35.getTextCotacao() );
        txvOddMenos_35.setText( wgtMenos_35.getTextCotacao() );

        return this;
    }
}
