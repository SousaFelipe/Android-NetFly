package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.primeiras.ResultadoP;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class ResultadoPView extends BaseOddsView {



    private ResultadoP resultadoP;

    private Widget wgtCasa;
    private Widget wgtEmpate;
    private Widget wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddEmpate;
    private TextView txvOddFora;



    public ResultadoPView(Context context, ResultadoP resultadoP) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_principal_pt, null, false));

        setContext(context);
        setAposta(new Aposta(ResultadoP.TIPO).partida(resultadoP.partida));

        this.resultadoP = resultadoP;
    }


    @Override
    public ResultadoPView create() {

        Aposta casa = new Aposta(ResultadoP.TIPO).partida(resultadoP.partida).titulo("1° Tempo - Casa").sentenca("C").cotacao(resultadoP.casa);
        Aposta empate = new Aposta(ResultadoP.TIPO).partida(resultadoP.partida).titulo("1° Tempo - Empate").sentenca("E").cotacao(resultadoP.empate);
        Aposta fora = new Aposta(ResultadoP.TIPO).partida(resultadoP.partida).titulo("1° Tempo - Fora").sentenca("F").cotacao(resultadoP.fora);

        wgtCasa = new Widget(casa, getRootView().findViewById(R.id.layout_odd_casa_pt));
        wgtEmpate = new Widget(empate, getRootView().findViewById(R.id.layout_odd_empate_pt));
        wgtFora = new Widget(fora, getRootView().findViewById(R.id.layout_odd_fora_pt));

        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa);
        txvOddEmpate = getRootView().findViewById(R.id.txv_odd_empate);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora);

        return this;
    }


    @Override
    public ResultadoPView build() {
        txvOddCasa.setText( wgtCasa.getTextCotacao() );
        txvOddEmpate.setText( wgtEmpate.getTextCotacao() );
        txvOddFora.setText( wgtFora.getTextCotacao() );
        return this;
    }

    

    public ResultadoPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
