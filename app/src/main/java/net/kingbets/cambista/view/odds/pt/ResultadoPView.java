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

        wgtCasa = new Widget(getAposta("C"), getRootView().findViewById(R.id.layout_odd_casa_pt), resultadoP.casa);
        wgtCasa.setTitulo("1° Tempo - Casa");

        wgtEmpate = new Widget(getAposta("E"), getRootView().findViewById(R.id.layout_odd_empate_pt), resultadoP.empate);
        wgtEmpate.setTitulo("1° Tempo - Empate");

        wgtFora = new Widget(getAposta("F"), getRootView().findViewById(R.id.layout_odd_fora_pt), resultadoP.fora);
        wgtFora.setTitulo("1° Tempo - Fora");

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
