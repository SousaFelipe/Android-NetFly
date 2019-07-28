package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.Resultado;
import net.kingbets.cambista.view.widgets.Widget;


public class ResultadoView extends BaseOddsView {



    private Resultado resultado;

    private Widget wgtCasa;
    private Widget wgtEmpate;
    private Widget wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoView(Context context, Resultado resultado) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal, null, false));
        setContext(context);
        this.resultado = resultado;
    }


    @Override
    public ResultadoView create() {

        Aposta casa = new Aposta(Resultado.TIPO).partida(resultado.partida).titulo("Casa").sentenca("C").cotacao(resultado.casa);
        Aposta empate = new Aposta(Resultado.TIPO).partida(resultado.partida).titulo("Empate").sentenca("E").cotacao(resultado.empate);
        Aposta fora = new Aposta(Resultado.TIPO).partida(resultado.partida).titulo("Fora").sentenca("F").cotacao(resultado.fora);

        wgtCasa = new Widget(casa, getRootView().findViewById(R.id.layout_odd_casa));
        wgtEmpate = new Widget(empate, getRootView().findViewById(R.id.layout_odd_empate));
        wgtFora = new Widget(fora, getRootView().findViewById(R.id.layout_odd_fora));

        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoView build() {
        txvOddCasa.setText( wgtCasa.getTextCotacao() );
        txvOddEmpate.setText( wgtEmpate.getTextCotacao() );
        txvOddFora.setText( wgtFora.getTextCotacao() );
        return this;
    }

    

    public ResultadoView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
