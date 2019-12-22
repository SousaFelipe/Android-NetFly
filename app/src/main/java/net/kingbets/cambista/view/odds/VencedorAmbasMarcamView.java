package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.VencedorAmbasMarcam;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class VencedorAmbasMarcamView extends BaseOddsView {



    private VencedorAmbasMarcam vencedorAmbasMarcam;

    private WidgetOdd wgtCasaSim;
    private WidgetOdd wgtCasaNao;
    private WidgetOdd wgtForaSim;
    private WidgetOdd wgtForaNao;
    private WidgetOdd wgtEmpateSim;
    private WidgetOdd wgtEmpateNao;

    private TextView txvOddCasaSim;
    private TextView txvOddCasaNao;
    private TextView txvOddForaSim;
    private TextView txvOddForaNao;
    private TextView txvOddEmpateSim;
    private TextView txvOddEmpateNao;



    public VencedorAmbasMarcamView(Context context, VencedorAmbasMarcam vencedorAmbasMarcam) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_vencedor_ambas_marcam, null, false));
        setContext(context);
        this.vencedorAmbasMarcam = vencedorAmbasMarcam;
    }


    @Override
    public VencedorAmbasMarcamView create(BaseFragment parent) {

        Bet casaS = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Casa x Ambas Marcam: SIM").sentenca("C;S").cotacao(vencedorAmbasMarcam.casaSim);
        Bet casaN = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Casa x Ambas Marcam: NAO").sentenca("C;N").cotacao(vencedorAmbasMarcam.casaNao);
        Bet foraS = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Fora x Ambas Marcam: SIM").sentenca("F;S").cotacao(vencedorAmbasMarcam.foraSim);
        Bet foraN = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Fora x Ambas Marcam: NAO").sentenca("F;N").cotacao(vencedorAmbasMarcam.foraNao);
        Bet empaS = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Empate x Ambas Marcam: SIM").sentenca("E;S").cotacao(vencedorAmbasMarcam.empateSim);
        Bet empaN = new Bet(VencedorAmbasMarcam.TIPO).odd(vencedorAmbasMarcam.id).partida(vencedorAmbasMarcam.partida).titulo("Empate x Ambas Marcam: NAO").sentenca("E;N").cotacao(vencedorAmbasMarcam.empateNao);

        wgtCasaSim   = new WidgetOdd(casaS, getRootView().findViewById(R.id.layout_odd_casa_sim), parent);
        wgtCasaNao   = new WidgetOdd(casaN, getRootView().findViewById(R.id.layout_odd_casa_nao), parent);
        wgtForaSim   = new WidgetOdd(foraS, getRootView().findViewById(R.id.layout_odd_fora_sim), parent);
        wgtForaNao   = new WidgetOdd(foraN, getRootView().findViewById(R.id.layout_odd_fora_nao), parent);
        wgtEmpateSim = new WidgetOdd(empaS, getRootView().findViewById(R.id.layout_odd_empate_sim), parent);
        wgtEmpateNao = new WidgetOdd(empaN, getRootView().findViewById(R.id.layout_odd_empate_nao), parent);

        txvOddCasaSim   = getRootView().findViewById(R.id.txv_odd_casa_sim);
        txvOddCasaNao   = getRootView().findViewById(R.id.txv_odd_casa_nao);
        txvOddForaSim   = getRootView().findViewById(R.id.txv_odd_fora_sim);
        txvOddForaNao   = getRootView().findViewById(R.id.txv_odd_fora_nao);
        txvOddEmpateSim = getRootView().findViewById(R.id.txv_odd_empate_sim);
        txvOddEmpateNao = getRootView().findViewById(R.id.txv_odd_empate_nao);

        return this;
    }


    @Override
    public VencedorAmbasMarcamView build() {

        txvOddCasaSim.setText( wgtCasaSim.getTextOdd() );
        txvOddCasaNao.setText( wgtCasaNao.getTextOdd() );
        txvOddForaSim.setText( wgtForaSim.getTextOdd() );
        txvOddForaNao.setText( wgtForaNao.getTextOdd() );
        txvOddEmpateSim.setText( wgtEmpateSim.getTextOdd() );
        txvOddEmpateNao.setText( wgtEmpateNao.getTextOdd() );

        wgtCasaSim.refresh();
        wgtCasaNao.refresh();
        wgtForaSim.refresh();
        wgtForaNao.refresh();
        wgtEmpateSim.refresh();
        wgtEmpateNao.refresh();

        return this;
    }
}
