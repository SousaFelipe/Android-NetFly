package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.Resultado;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ResultadoView extends BaseOddsView {



    private Resultado resultado;

    private WidgetOdd wgtCasa;
    private WidgetOdd wgtEmpate;
    private WidgetOdd wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoView(Context context, Resultado resultado) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal, null, false));
        setContext(context);
        this.resultado = resultado;
    }


    @Override
    public ResultadoView create(BaseFragment parent) {

        Bet casa    = new Bet(Resultado.TIPO).odd(resultado.id).partida(resultado.partida).titulo("Casa").sentenca("C").cotacao(resultado.casa);
        Bet empate  = new Bet(Resultado.TIPO).odd(resultado.id).partida(resultado.partida).titulo("Empate").sentenca("E").cotacao(resultado.empate);
        Bet fora    = new Bet(Resultado.TIPO).odd(resultado.id).partida(resultado.partida).titulo("Fora").sentenca("F").cotacao(resultado.fora);

        wgtCasa     = new WidgetOdd(casa, getRootView().findViewById(R.id.layout_odd_casa), parent);
        wgtEmpate   = new WidgetOdd(empate, getRootView().findViewById(R.id.layout_odd_empate), parent);
        wgtFora     = new WidgetOdd(fora, getRootView().findViewById(R.id.layout_odd_fora), parent);

        txvOddCasa      = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate    = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora      = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoView build() {

        txvOddCasa.setText( wgtCasa.getTextOdd() );
        txvOddEmpate.setText( wgtEmpate.getTextOdd() );
        txvOddFora.setText( wgtFora.getTextOdd() );

        wgtCasa.refresh();
        wgtEmpate.refresh();
        wgtFora.refresh();

        return this;
    }

    

    public ResultadoView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
