package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.segundas.AmbasMarcamS;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class AmbasMarcamSView extends BaseOddsView {



    private AmbasMarcamS ambasMarcamS;


    private WidgetOdd wgtSim;
    private WidgetOdd wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamSView(Context context, AmbasMarcamS ambasMarcamS) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam_st, null, false));
        setContext(context);
        this.ambasMarcamS = ambasMarcamS;
    }



    @Override
    public AmbasMarcamSView create(BaseFragment parent) {

        Bet sim = new Bet(AmbasMarcamS.TIPO).odd(ambasMarcamS.id).partida(ambasMarcamS.partida).titulo("2° Tempo - Ambas Marcam: SIM").sentenca("S").cotacao(ambasMarcamS.sim);
        Bet nao = new Bet(AmbasMarcamS.TIPO).odd(ambasMarcamS.id).partida(ambasMarcamS.partida).titulo("2° Tempo - Ambas Marcam: NAO").sentenca("N").cotacao(ambasMarcamS.nao);

        wgtSim = new WidgetOdd(sim, getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim_st), parent);
        wgtNao = new WidgetOdd(nao, getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao_st), parent);

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamSView build() {

        txvOddSim.setText( wgtSim.getTextOdd() );
        txvOddNao.setText( wgtNao.getTextOdd() );

        wgtSim.refresh();
        wgtNao.refresh();

        return this;
    }



    public AmbasMarcamSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
