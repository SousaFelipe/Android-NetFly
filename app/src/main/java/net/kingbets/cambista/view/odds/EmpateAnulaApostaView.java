package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.EmpateAnulaAposta;
import net.kingbets.cambista.view.widgets.Widget;


public class EmpateAnulaApostaView extends BaseOddsView {



    private EmpateAnulaAposta empateAnulaAposta;

    private Widget wgtCasa;
    private Widget wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddFora;



    public EmpateAnulaApostaView(Context context, EmpateAnulaAposta empateAnulaAposta) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_empate_anula_aposta, null, false));

        setContext(context);
        setAposta(new Aposta(EmpateAnulaAposta.TIPO).partida(empateAnulaAposta.partida));

        this.empateAnulaAposta = empateAnulaAposta;
    }


    @Override
    public EmpateAnulaApostaView create() {

        wgtCasa = new Widget(getAposta("C"), getRootView().findViewById(R.id.layout_odd_casa_ou_anula), empateAnulaAposta.casaOuAnula);
        wgtCasa.setTitulo("Casa ou Anula Aposta");

        wgtFora = new Widget(getAposta("F"), getRootView().findViewById(R.id.layout_odd_fora_ou_anula), empateAnulaAposta.foraOuAnula);
        wgtFora.setTitulo("Fora ou Anula Aposta");


        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa_ou_anula);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora_ou_anula);

        return this;
    }


    @Override
    public EmpateAnulaApostaView build() {
        txvOddCasa.setText( wgtCasa.getTextCotacao() );
        txvOddFora.setText( wgtFora.getTextCotacao() );
        return this;
    }
}
