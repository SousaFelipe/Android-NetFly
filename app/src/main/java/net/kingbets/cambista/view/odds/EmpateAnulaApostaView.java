package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.EmpateAnulaAposta;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class EmpateAnulaApostaView extends BaseOddsView {



    private EmpateAnulaAposta empateAnulaAposta;

    private WidgetOdd wgtCasa;
    private WidgetOdd wgtFora;

    private TextView txvOddCasa;
    private TextView txvOddFora;



    public EmpateAnulaApostaView(Context context, EmpateAnulaAposta empateAnulaAposta) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_empate_anula_aposta, null, false));
        setContext(context);
        this.empateAnulaAposta = empateAnulaAposta;
    }


    @Override
    public EmpateAnulaApostaView create(BaseFragment parent) {

        Bet casa = new Bet(EmpateAnulaAposta.TIPO).odd(empateAnulaAposta.id).partida(empateAnulaAposta.partida).titulo("Empate Anula Aposta: Casa").sentenca("C").cotacao(empateAnulaAposta.casaOuAnula);
        Bet fora = new Bet(EmpateAnulaAposta.TIPO).odd(empateAnulaAposta.id).partida(empateAnulaAposta.partida).titulo("Empate Anula Aposta: Fora").sentenca("F").cotacao(empateAnulaAposta.foraOuAnula);

        wgtCasa = new WidgetOdd(casa, getRootView().findViewById(R.id.layout_odd_casa_ou_anula), parent);
        wgtFora = new WidgetOdd(fora, getRootView().findViewById(R.id.layout_odd_fora_ou_anula), parent);

        txvOddCasa = getRootView().findViewById(R.id.txv_odd_casa_ou_anula);
        txvOddFora = getRootView().findViewById(R.id.txv_odd_fora_ou_anula);

        return this;
    }


    @Override
    public EmpateAnulaApostaView build() {

        txvOddCasa.setText( wgtCasa.getTextOdd() );
        txvOddFora.setText( wgtFora.getTextOdd() );

        wgtCasa.refresh();
        wgtFora.refresh();

        return this;
    }
}
