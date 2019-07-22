package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
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
        setAposta(new Aposta(Resultado.TIPO).withPartida(resultado.partida));

        this.resultado = resultado;
    }


    @Override
    public ResultadoView create() {

        wgtCasa = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa), resultado.casa);
        wgtEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate), resultado.empate);
        wgtFora = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora), resultado.fora);

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
