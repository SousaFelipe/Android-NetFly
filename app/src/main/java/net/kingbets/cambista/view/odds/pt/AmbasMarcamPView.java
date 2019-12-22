package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.primeiras.AmbasMarcamP;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class AmbasMarcamPView extends BaseOddsView {



    private AmbasMarcamP ambasMarcamP;


    private WidgetOdd wgtSim;
    private WidgetOdd wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamPView(Context context, AmbasMarcamP ambasMarcamP) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam_pt, null, false));
        setContext(context);
        this.ambasMarcamP = ambasMarcamP;
    }



    @Override
    public AmbasMarcamPView create(BaseFragment parent) {

        Bet sim = new Bet(AmbasMarcamP.TIPO).odd(ambasMarcamP.id).partida(ambasMarcamP.partida).titulo("1° Tempo - Ambas Marcam: SIM").sentenca("S").cotacao(ambasMarcamP.sim);
        Bet nao = new Bet(AmbasMarcamP.TIPO).odd(ambasMarcamP.id).partida(ambasMarcamP.partida).titulo("1° Tempo - Ambas Marcam: NAO").sentenca("N").cotacao(ambasMarcamP.nao);

        wgtSim = new WidgetOdd(sim, getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim_pt), parent);
        wgtNao = new WidgetOdd(nao, getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao_pt), parent);

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamPView build() {

        txvOddSim.setText( wgtSim.getTextOdd() );
        txvOddNao.setText( wgtNao.getTextOdd() );

        wgtSim.refresh();
        wgtNao.refresh();

        return this;
    }



    public AmbasMarcamPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
