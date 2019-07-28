package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
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
        setAposta(new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida));

        this.intervaloFinal = intervaloFinal;
    }


    @Override
    public IntervaloFinalView create() {

        Aposta cc = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Casa x Casa").sentenca("C;C").cotacao(intervaloFinal.casaCasa);
        Aposta ce = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Casa x Empate").sentenca("C;E").cotacao(intervaloFinal.casaEmpate);
        Aposta cf = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Casa x Fora").sentenca("C;F").cotacao(intervaloFinal.casaFora);
        Aposta ec = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Empate x Casa").sentenca("E;C").cotacao(intervaloFinal.empateCasa);
        Aposta ee = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Empate x Empate").sentenca("E;E").cotacao(intervaloFinal.empateEmpate);
        Aposta ef = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Empate x Fora").sentenca("E;F").cotacao(intervaloFinal.empateFora);
        Aposta fc = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Fora x Casa").sentenca("F;C").cotacao(intervaloFinal.foraCasa);
        Aposta fe = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Fora x Empate").sentenca("F;E").cotacao(intervaloFinal.foraEmpate);
        Aposta ff = new Aposta(IntervaloFinal.TIPO).partida(intervaloFinal.partida).titulo("Fora x Fora").sentenca("F;F").cotacao(intervaloFinal.foraFora);

        wgtCasaCasa = new Widget(cc, getRootView().findViewById(R.id.layout_odd_casa_casa));
        wgtCasaEmpate = new Widget(ce, getRootView().findViewById(R.id.layout_odd_casa_empate));
        wgtCasaFora = new Widget(cf, getRootView().findViewById(R.id.layout_odd_casa_fora));
        wgtEmpateCasa = new Widget(ec, getRootView().findViewById(R.id.layout_odd_empate_casa));
        wgtEmpateEmpate = new Widget(ee, getRootView().findViewById(R.id.layout_odd_empate_empate));
        wgtEmpateFora = new Widget(ef, getRootView().findViewById(R.id.layout_odd_empate_fora));
        wgtForaCasa = new Widget(fc, getRootView().findViewById(R.id.layout_odd_fora_casa));
        wgtForaEmpate = new Widget(fe, getRootView().findViewById(R.id.layout_odd_fora_empate));
        wgtForaFora = new Widget(ff, getRootView().findViewById(R.id.layout_odd_fora_fora));


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
