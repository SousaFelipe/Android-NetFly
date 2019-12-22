package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.IntervaloFinal;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class IntervaloFinalView extends BaseOddsView {



    private IntervaloFinal intervaloFinal;

    private WidgetOdd wgtCasaCasa;
    private WidgetOdd wgtCasaEmpate;
    private WidgetOdd wgtCasaFora;
    private WidgetOdd wgtEmpateCasa;
    private WidgetOdd wgtEmpateEmpate;
    private WidgetOdd wgtEmpateFora;
    private WidgetOdd wgtForaCasa;
    private WidgetOdd wgtForaEmpate;
    private WidgetOdd wgtForaFora;

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
        this.intervaloFinal = intervaloFinal;
    }


    @Override
    public IntervaloFinalView create(BaseFragment parent) {

        Bet cc = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Casa x Final: Casa").sentenca("C;C").cotacao(intervaloFinal.casaCasa);
        Bet ce = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Casa x Final: Empate").sentenca("C;E").cotacao(intervaloFinal.casaEmpate);
        Bet cf = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Casa x Final: Fora").sentenca("C;F").cotacao(intervaloFinal.casaFora);
        Bet ec = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Empate x Final: Casa").sentenca("E;C").cotacao(intervaloFinal.empateCasa);
        Bet ee = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Empate x Final: Empate").sentenca("E;E").cotacao(intervaloFinal.empateEmpate);
        Bet ef = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Empate x Final: Fora").sentenca("E;F").cotacao(intervaloFinal.empateFora);
        Bet fc = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Fora x Final: Casa").sentenca("F;C").cotacao(intervaloFinal.foraCasa);
        Bet fe = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Fora x Final: Empate").sentenca("F;E").cotacao(intervaloFinal.foraEmpate);
        Bet ff = new Bet(IntervaloFinal.TIPO).odd(intervaloFinal.id).partida(intervaloFinal.partida).titulo("Intervalo: Fora x Final: Fora").sentenca("F;F").cotacao(intervaloFinal.foraFora);

        wgtCasaCasa     = new WidgetOdd(cc, getRootView().findViewById(R.id.layout_odd_casa_casa), parent);
        wgtCasaEmpate   = new WidgetOdd(ce, getRootView().findViewById(R.id.layout_odd_casa_empate), parent);
        wgtCasaFora     = new WidgetOdd(cf, getRootView().findViewById(R.id.layout_odd_casa_fora), parent);
        wgtEmpateCasa   = new WidgetOdd(ec, getRootView().findViewById(R.id.layout_odd_empate_casa), parent);
        wgtEmpateEmpate = new WidgetOdd(ee, getRootView().findViewById(R.id.layout_odd_empate_empate), parent);
        wgtEmpateFora   = new WidgetOdd(ef, getRootView().findViewById(R.id.layout_odd_empate_fora), parent);
        wgtForaCasa     = new WidgetOdd(fc, getRootView().findViewById(R.id.layout_odd_fora_casa), parent);
        wgtForaEmpate   = new WidgetOdd(fe, getRootView().findViewById(R.id.layout_odd_fora_empate), parent);
        wgtForaFora     = new WidgetOdd(ff, getRootView().findViewById(R.id.layout_odd_fora_fora), parent);

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

        txvCasaCasa.setText( wgtCasaCasa.getTextOdd() );
        txvCasaEmpate.setText( wgtCasaEmpate.getTextOdd() );
        txvCasaFora.setText( wgtCasaFora.getTextOdd() );
        txvEmpateCasa.setText( wgtEmpateCasa.getTextOdd() );
        txvEmpateEmpate.setText( wgtEmpateEmpate.getTextOdd() );
        txvEmpateFora.setText( wgtEmpateFora.getTextOdd() );
        txvForaCasa.setText( wgtForaCasa.getTextOdd() );
        txvForaEmpate.setText( wgtForaEmpate.getTextOdd() );
        txvForaFora.setText( wgtForaFora.getTextOdd() );

        wgtCasaCasa.refresh();
        wgtCasaEmpate.refresh();
        wgtCasaFora.refresh();
        wgtEmpateCasa.refresh();
        wgtEmpateEmpate.refresh();
        wgtEmpateFora.refresh();
        wgtForaCasa.refresh();
        wgtForaEmpate.refresh();
        wgtForaFora.refresh();

        return this;
    }
}
