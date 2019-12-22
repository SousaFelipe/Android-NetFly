package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.AmbasMarcam;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class AmbasMarcamView extends BaseOddsView {



    private AmbasMarcam ambasMarcam;


    private WidgetOdd wgtSim;
    private WidgetOdd wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamView(Context context, AmbasMarcam ambasMarcam) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam, null, false));
        setContext(context);
        this.ambasMarcam = ambasMarcam;
    }



    @Override
    public AmbasMarcamView create(BaseFragment parent) {

        Bet sim = new Bet(AmbasMarcam.TIPO).odd(ambasMarcam.id).partida(ambasMarcam.partida).titulo("Ambas Marcam: SIM").sentenca("S").cotacao(ambasMarcam.sim);
        Bet nao = new Bet(AmbasMarcam.TIPO).odd(ambasMarcam.id).partida(ambasMarcam.partida).titulo("Ambas Marcam: NAO").sentenca("N").cotacao(ambasMarcam.nao);


        wgtSim = new WidgetOdd(sim, getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim), parent);
        wgtNao = new WidgetOdd(nao, getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao), parent);

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamView build() {

        txvOddSim.setText(wgtSim.getTextOdd());
        txvOddNao.setText(wgtNao.getTextOdd());

        wgtSim.refresh();
        wgtNao.refresh();

        return this;
    }



    public AmbasMarcamView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
