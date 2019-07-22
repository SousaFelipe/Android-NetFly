package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.IntervaloFinal;
import net.kingbets.cambista.view.widgets.Widget;


public class IntervaloFinalView extends BaseOddsView {



    private IntervaloFinal intervaloFinal;

    private Widget wgtCasaCasa;
    private Widget wgtCasaEmpate;
    private Widget wgtCasaFora;
    private Widget wgtEmpateCasa;
    private Widget wgtEmpateEmpate;
    private Widget wgtEmpateFora;
    private Widget wgtForaCasa;
    private Widget wgtForaEmpate;
    private Widget wgtForaFora;

    private TextView txvCasaCasa;
    private TextView txvCasaEmpate;
    private TextView txvCasaFora;
    private TextView txvEmpateCasa;
    private TextView txvEmpateEmpate;
    private TextView txvEmpateFora;
    private TextView txvForaCasa;
    private TextView txvForaEmpate;
    private TextView txvForaFora;



    public IntervaloFinalView(Context context, IntervaloFinal intervaloFinal) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_intervalo_final, null, false));

        setContext(context);
        setAposta(new Aposta(IntervaloFinal.TIPO).withPartida(intervaloFinal.partida));

        this.intervaloFinal = intervaloFinal;
    }


    @Override
    public IntervaloFinalView create() {

        wgtCasaCasa = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_casa), intervaloFinal.casaCasa);
        wgtCasaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_empate), intervaloFinal.casaEmpate);
        wgtCasaFora = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_fora), intervaloFinal.casaFora);
        wgtEmpateCasa = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate_casa), intervaloFinal.empateCasa);
        wgtEmpateEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate_empate), intervaloFinal.empateEmpate);
        wgtEmpateFora = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate_fora), intervaloFinal.empateFora);
        wgtForaCasa = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_casa), intervaloFinal.foraCasa);
        wgtForaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_empate), intervaloFinal.foraEmpate);
        wgtForaFora = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_fora), intervaloFinal.foraFora);

        txvCasaCasa = getRootView().findViewById(R.id.txv_odd_casa_casa);
        txvCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);
        txvEmpateCasa = getRootView().findViewById(R.id.txv_odd_empate_casa);
        txvEmpateEmpate = getRootView().findViewById(R.id.txv_odd_empate_empate);
        txvEmpateFora = getRootView().findViewById(R.id.txv_odd_empate_fora);
        txvForaCasa = getRootView().findViewById(R.id.txv_odd_fora_casa);
        txvForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvForaFora = getRootView().findViewById(R.id.txv_odd_fora_fora);

        return this;
    }


    @Override
    public IntervaloFinalView build() {

        txvCasaCasa.setText( wgtCasaCasa.getTextCotacao() );
        txvCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvCasaFora.setText( wgtCasaFora.getTextCotacao() );
        txvEmpateCasa.setText( wgtEmpateCasa.getTextCotacao() );
        txvEmpateEmpate.setText( wgtEmpateEmpate.getTextCotacao() );
        txvEmpateFora.setText( wgtEmpateFora.getTextCotacao() );
        txvForaCasa.setText( wgtForaCasa.getTextCotacao() );
        txvForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvForaFora.setText( wgtForaFora.getTextCotacao() );

        return this;
    }
}
