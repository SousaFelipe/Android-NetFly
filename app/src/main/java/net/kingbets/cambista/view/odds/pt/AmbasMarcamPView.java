package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.primeiras.AmbasMarcamP;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class AmbasMarcamPView extends BaseOddsView {



    private AmbasMarcamP ambasMarcamP;


    private Widget wgtSim;
    private Widget wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamPView(Context context, AmbasMarcamP ambasMarcamP) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam_pt, null, false));

        setContext(context);
        setAposta(new Aposta(AmbasMarcamP.TIPO).withPartida(ambasMarcamP.partida));

        this.ambasMarcamP = ambasMarcamP;
    }



    @Override
    public AmbasMarcamPView create() {

        wgtSim = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim_pt), ambasMarcamP.sim);
        wgtNao = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao_pt), ambasMarcamP.nao);

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamPView build() {
        txvOddSim.setText( wgtSim.getTextCotacao() );
        txvOddNao.setText( wgtNao.getTextCotacao() );
        return this;
    }



    public AmbasMarcamPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
