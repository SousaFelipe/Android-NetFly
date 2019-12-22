package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.primeiras.DuplaChanceP;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class DuplaChancePView extends BaseOddsView {



    private DuplaChanceP duplaChanceP;


    private WidgetOdd wgtCasaEmpate;
    private WidgetOdd wgtForaEmpate;
    private WidgetOdd wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChancePView(Context context, DuplaChanceP duplaChanceP) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance_pt, null, false) );
        setContext(context);
        this.duplaChanceP = duplaChanceP;
    }


    @Override
    public DuplaChancePView create(BaseFragment parent) {

        Bet casaE = new Bet(DuplaChanceP.TIPO).odd(duplaChanceP.id).partida(duplaChanceP.partida).titulo("1° Tempo - Casa ou Empate").sentenca("C;E").cotacao(duplaChanceP.casaEmpate);
        Bet foraE = new Bet(DuplaChanceP.TIPO).odd(duplaChanceP.id).partida(duplaChanceP.partida).titulo("1° Tempo - Fora ou Empate").sentenca("F;E").cotacao(duplaChanceP.foraEmpate);
        Bet casaF = new Bet(DuplaChanceP.TIPO).odd(duplaChanceP.id).partida(duplaChanceP.partida).titulo("1° Tempo - Casa ou Fora").sentenca("C;F").cotacao(duplaChanceP.casaFora);

        wgtCasaEmpate = new WidgetOdd(casaE, getRootView().findViewById(R.id.layout_odd_casa_empate_pt), parent);
        wgtForaEmpate = new WidgetOdd(foraE, getRootView().findViewById(R.id.layout_odd_fora_empate_pt), parent);
        wgtCasaFora = new WidgetOdd(casaF, getRootView().findViewById(R.id.layout_odd_casa_fora_pt), parent);

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChancePView build() {

        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextOdd() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextOdd() );
        txvOddCasaFora.setText( wgtCasaFora.getTextOdd() );

        wgtCasaEmpate.refresh();
        wgtForaEmpate.refresh();
        wgtCasaFora.refresh();

        return this;
    }



    public DuplaChancePView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
