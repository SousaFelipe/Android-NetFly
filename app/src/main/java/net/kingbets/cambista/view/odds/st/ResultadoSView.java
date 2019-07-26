package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.Resultado;
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
        setAposta(new Aposta(ResultadoS.TIPO).withPartida(resultadoS.partida));

        this.resultadoS = resultadoS;
    }


    @Override
    public ResultadoSView create() {

        wgtCasa = new Widget(getAposta("C"), getRootView().findViewById(R.id.layout_odd_casa_st), resultadoS.casa);
        wgtCasa.setTitulo("2° Tempo - Casa");

        wgtEmpate = new Widget(getAposta("E"), getRootView().findViewById(R.id.layout_odd_empate_st), resultadoS.empate);
        wgtEmpate.setTitulo("2° Tempo - Empate");

        wgtFora = new Widget(getAposta("F"), getRootView().findViewById(R.id.layout_odd_fora_st), resultadoS.fora);
        wgtFora.setTitulo("2° Tempo - Fora");

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
