package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.segundas.ResultadoS;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ResultadoSView extends BaseOddsView {



    private ResultadoS resultadoS;

    private WidgetOdd wgtCasa;
    private WidgetOdd wgtEmpate;
    private WidgetOdd wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoSView(Context context, ResultadoS resultadoS) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal_st, null, false));
        setContext(context);
        this.resultadoS = resultadoS;
    }


    @Override
    public ResultadoSView create(BaseFragment parent) {

        Bet casa   = new Bet(ResultadoS.TIPO).odd(resultadoS.id).partida(resultadoS.partida).titulo("2° Tempo - Casa").sentenca("C").cotacao(resultadoS.casa);
        Bet empate = new Bet(ResultadoS.TIPO).odd(resultadoS.id).partida(resultadoS.partida).titulo("2° Tempo - Empate").sentenca("E").cotacao(resultadoS.empate);
        Bet fora   = new Bet(ResultadoS.TIPO).odd(resultadoS.id).partida(resultadoS.partida).titulo("2° Tempo - Fora").sentenca("F").cotacao(resultadoS.fora);

        wgtCasa   = new WidgetOdd(casa, getRootView().findViewById(R.id.layout_odd_casa_st), parent);
        wgtEmpate = new WidgetOdd(empate, getRootView().findViewById(R.id.layout_odd_empate_st), parent);
        wgtFora   = new WidgetOdd(fora, getRootView().findViewById(R.id.layout_odd_fora_st), parent);

        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoSView build() {

        txvOddCasa.setText( wgtCasa.getTextOdd() );
        txvOddEmpate.setText( wgtEmpate.getTextOdd() );
        txvOddFora.setText( wgtFora.getTextOdd() );

        wgtCasa.refresh();
        wgtEmpate.refresh();
        wgtFora.refresh();

        return this;
    }

    

    public ResultadoSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
