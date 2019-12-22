package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.VenceSemLevarGols;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class GanhaSemLevarGolsCasaView extends BaseOddsView {



    private VenceSemLevarGols venceSemLevarGols;


    private WidgetOdd wgtSim;
    private WidgetOdd wgtNao;

    private TextView txvOddSim;
    private TextView txvOddNao;



    public GanhaSemLevarGolsCasaView(Context context, VenceSemLevarGols venceSemLevarGols) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_vence_sem_levar_gols_casa, null, false));
        setContext(context);
        this.venceSemLevarGols = venceSemLevarGols;
    }



    @Override
    public GanhaSemLevarGolsCasaView create(BaseFragment parent) {

        Bet sim = new Bet(VenceSemLevarGols.TIPO).odd(venceSemLevarGols.id).partida(venceSemLevarGols.partida).titulo("Casa Ganha Sem Levar Gols: SIM").sentenca("C;S").cotacao(venceSemLevarGols.sim);
        Bet nao = new Bet(VenceSemLevarGols.TIPO).odd(venceSemLevarGols.id).partida(venceSemLevarGols.partida).titulo("Casa Ganha Sem Levar Gols: NAO").sentenca("C;N").cotacao(venceSemLevarGols.nao);

        wgtSim = new WidgetOdd(sim, getRootView().findViewById(R.id.layout_odd_sim), parent);
        wgtNao = new WidgetOdd(nao, getRootView().findViewById(R.id.layout_odd_nao), parent);

        txvOddSim = getRootView().findViewById(R.id.txv_odd_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_nao);

        return this;
    }



    @Override
    public GanhaSemLevarGolsCasaView build() {

        txvOddSim.setText( wgtSim.getTextOdd() );
        txvOddNao.setText( wgtNao.getTextOdd() );

        wgtSim.refresh();
        wgtNao.refresh();

        return this;
    }
}
