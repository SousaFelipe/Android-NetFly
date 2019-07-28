package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.AmbasMarcamS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class AmbasMarcamSView extends BaseOddsView {



    private AmbasMarcamS ambasMarcamS;


    private Widget wgtSim;
    private Widget wgtNao;


    private TextView txvOddSim;
    private TextView txvOddNao;



    public AmbasMarcamSView(Context context, AmbasMarcamS ambasMarcamS) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_ambas_marcam_st, null, false));

        setContext(context);
        setAposta(new Aposta(AmbasMarcamS.TIPO).partida(ambasMarcamS.partida));

        this.ambasMarcamS = ambasMarcamS;
    }



    @Override
    public AmbasMarcamSView create() {

        Aposta sim = new Aposta(AmbasMarcamS.TIPO).partida(ambasMarcamS.partida).titulo("2° Tempo - Ambas Marcam: SIM").sentenca("S").cotacao(ambasMarcamS.sim);
        Aposta nao = new Aposta(AmbasMarcamS.TIPO).partida(ambasMarcamS.partida).titulo("2° Tempo - Ambas Marcam: NAO").sentenca("N").cotacao(ambasMarcamS.nao);

        wgtSim = new Widget(sim, getRootView().findViewById(R.id.layout_odd_ambas_marcam_sim_st));
        wgtNao = new Widget(nao, getRootView().findViewById(R.id.layout_odd_ambas_marcam_nao_st));

        txvOddSim = getRootView().findViewById(R.id.txv_odd_ambas_marcam_sim);
        txvOddNao = getRootView().findViewById(R.id.txv_odd_ambas_marcam_nao);

        return this;
    }



    @Override
    public AmbasMarcamSView build() {
        txvOddSim.setText( wgtSim.getTextCotacao() );
        txvOddNao.setText( wgtNao.getTextCotacao() );
        return this;
    }



    public AmbasMarcamSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
