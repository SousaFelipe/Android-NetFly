package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.ResultadoS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class ResultadoSView extends BaseOddsView {



    private ResultadoS resultadoS;

    private Widget wgtCasa;
    private Widget wgtEmpate;
    private Widget wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoSView(Context context, ResultadoS resultadoS) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal_st, null, false));

        setContext(context);
        setAposta(new Aposta(ResultadoS.TIPO).partida(resultadoS.partida));

        this.resultadoS = resultadoS;
    }


    @Override
    public ResultadoSView create() {

        Aposta casa = new Aposta(ResultadoS.TIPO).partida(resultadoS.partida).titulo("2° Tempo - Casa").sentenca("C").cotacao(resultadoS.casa);
        Aposta empate = new Aposta(ResultadoS.TIPO).partida(resultadoS.partida).titulo("2° Tempo - Empate").sentenca("E").cotacao(resultadoS.empate);
        Aposta fora = new Aposta(ResultadoS.TIPO).partida(resultadoS.partida).titulo("2° Tempo - Fora").sentenca("F").cotacao(resultadoS.fora);

        wgtCasa = new Widget(casa, getRootView().findViewById(R.id.layout_odd_casa_st));
        wgtEmpate = new Widget(empate, getRootView().findViewById(R.id.layout_odd_empate_st));
        wgtFora = new Widget(fora, getRootView().findViewById(R.id.layout_odd_fora_st));

        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoSView build() {
        txvOddCasa.setText( wgtCasa.getTextCotacao() );
        txvOddEmpate.setText( wgtEmpate.getTextCotacao() );
        txvOddFora.setText( wgtFora.getTextCotacao() );
        return this;
    }

    

    public ResultadoSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
