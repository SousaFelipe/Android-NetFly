package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.primeiras.ResultadoP;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ResultadoPView extends BaseOddsView {



    private ResultadoP resultadoP;

    private WidgetOdd wgtCasa;
    private WidgetOdd wgtEmpate;
    private WidgetOdd wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoPView(Context context, ResultadoP resultadoP) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal_pt, null, false));
        setContext(context);
        this.resultadoP = resultadoP;
    }


    @Override
    public ResultadoPView create(BaseFragment parent) {

        Bet casa    = new Bet(ResultadoP.TIPO).odd(resultadoP.id).partida(resultadoP.partida).titulo("1° Tempo - Casa").sentenca("C").cotacao(resultadoP.casa);
        Bet empate  = new Bet(ResultadoP.TIPO).odd(resultadoP.id).partida(resultadoP.partida).titulo("1° Tempo - Empate").sentenca("E").cotacao(resultadoP.empate);
        Bet fora    = new Bet(ResultadoP.TIPO).odd(resultadoP.id).partida(resultadoP.partida).titulo("1° Tempo - Fora").sentenca("F").cotacao(resultadoP.fora);

        wgtCasa     = new WidgetOdd(casa, getRootView().findViewById(R.id.layout_odd_casa_pt), parent);
        wgtEmpate   = new WidgetOdd(empate, getRootView().findViewById(R.id.layout_odd_empate_pt), parent);
        wgtFora     = new WidgetOdd(fora, getRootView().findViewById(R.id.layout_odd_fora_pt), parent);

        txvOddCasa      = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate    = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora      = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoPView build() {

        txvOddCasa.setText( wgtCasa.getTextOdd() );
        txvOddEmpate.setText( wgtEmpate.getTextOdd() );
        txvOddFora.setText( wgtFora.getTextOdd() );

        wgtCasa.refresh();
        wgtEmpate.refresh();
        wgtFora.refresh();

        return this;
    }

    

    public ResultadoPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
