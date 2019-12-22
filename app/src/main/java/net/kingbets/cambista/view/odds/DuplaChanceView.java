package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.DuplaChance;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class DuplaChanceView extends BaseOddsView {



    private DuplaChance duplaChance;


    private WidgetOdd wgtCasaEmpate;
    private WidgetOdd wgtForaEmpate;
    private WidgetOdd wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceView(Context context, DuplaChance duplaChance) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance, null, false));
        setContext(context);
        this.duplaChance = duplaChance;
    }


    @Override
    public DuplaChanceView create(BaseFragment parent) {

        Bet casaE = new Bet(DuplaChance.TIPO).odd(duplaChance.id).partida(duplaChance.partida).titulo("Casa ou Empate").sentenca("C;E").cotacao(duplaChance.casaEmpate);
        Bet foraE = new Bet(DuplaChance.TIPO).odd(duplaChance.id).partida(duplaChance.partida).titulo("Fora ou Empate").sentenca("F;E").cotacao(duplaChance.foraEmpate);
        Bet casaF = new Bet(DuplaChance.TIPO).odd(duplaChance.id).partida(duplaChance.partida).titulo("Casa ou Fora").sentenca("C;F").cotacao(duplaChance.casaFora);

        wgtCasaEmpate = new WidgetOdd(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate), parent);
        wgtForaEmpate = new WidgetOdd(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate), parent);
        wgtCasaFora = new WidgetOdd(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora), parent);

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceView build() {

        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextOdd() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextOdd() );
        txvOddCasaFora.setText( wgtCasaFora.getTextOdd() );

        wgtCasaEmpate.refresh();
        wgtForaEmpate.refresh();
        wgtCasaFora.refresh();

        return this;
    }



    public DuplaChanceView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
