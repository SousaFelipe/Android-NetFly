package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.AmbasMarcam;
import net.kingbets.cambista.view.widgets.Widget;


public class AmbasMarcamView extends BaseOddsView {



    private AmbasMarcam ambasMarcam;


    private Widget wgtSim;
    private Widget wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamView(Context context, AmbasMarcam ambasMarcam) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam, null, false));

        setContext(context);
        setAposta(new Aposta(AmbasMarcam.TIPO).partida(ambasMarcam.partida));

        this.ambasMarcam = ambasMarcam;
    }



    @Override
    public AmbasMarcamView create() {

        wgtSim = new Widget(getAposta("S"), getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim), ambasMarcam.sim);
        wgtSim.setTitulo("Ambas Marcam");

        wgtNao = new Widget(getAposta("N"), getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao), ambasMarcam.nao);
        wgtNao.setTitulo("Ninguem Marca");

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamView build() {
        txvOddSim.setText( wgtSim.getTextCotacao() );
        txvOddNao.setText( wgtNao.getTextCotacao() );
        return this;
    }



    public AmbasMarcamView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
