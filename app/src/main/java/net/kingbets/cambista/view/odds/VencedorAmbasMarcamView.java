package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.VencedorAmbasMarcam;
import net.kingbets.cambista.view.widgets.Widget;


public class VencedorAmbasMarcamView extends BaseOddsView {



    private VencedorAmbasMarcam vencedorAmbasMarcam;

    private Widget wgtCasaSim;
    private Widget wgtCasaNao;
    private Widget wgtForaSim;
    private Widget wgtForaNao;
    private Widget wgtEmpateSim;
    private Widget wgtEmpateNao;

    private TextView txvOddCasaSim;
    private TextView txvOddCasaNao;
    private TextView txvOddForaSim;
    private TextView txvOddForaNao;
    private TextView txvOddEmpateSim;
    private TextView txvOddEmpateNao;



    public VencedorAmbasMarcamView(Context context, VencedorAmbasMarcam vencedorAmbasMarcam) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_vencedor_ambas_marcam, null, false));

        setContext(context);
        setAposta(new Aposta(VencedorAmbasMarcam.TIPO).withPartida(vencedorAmbasMarcam.partida));

        this.vencedorAmbasMarcam = vencedorAmbasMarcam;
    }


    @Override
    public VencedorAmbasMarcamView create() {

        wgtCasaSim = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_sim), vencedorAmbasMarcam.casaSim);
        wgtCasaNao = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_nao), vencedorAmbasMarcam.casaNao);
        wgtForaSim = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_sim), vencedorAmbasMarcam.foraSim);
        wgtForaNao = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_nao), vencedorAmbasMarcam.foraNao);
        wgtEmpateSim = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate_sim), vencedorAmbasMarcam.empateSim);
        wgtEmpateNao = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_empate_nao), vencedorAmbasMarcam.empateNao);

        txvOddCasaSim = getRootView().findViewById(R.id.txv_odd_casa_sim);
        txvOddCasaNao = getRootView().findViewById(R.id.txv_odd_casa_nao);
        txvOddForaSim = getRootView().findViewById(R.id.txv_odd_fora_sim);
        txvOddForaNao = getRootView().findViewById(R.id.txv_odd_fora_nao);
        txvOddEmpateSim = getRootView().findViewById(R.id.txv_odd_empate_sim);
        txvOddEmpateNao = getRootView().findViewById(R.id.txv_odd_empate_nao);

        return this;
    }


    @Override
    public VencedorAmbasMarcamView build() {
        txvOddCasaSim.setText( wgtCasaSim.getTextCotacao() );
        txvOddCasaNao.setText( wgtCasaNao.getTextCotacao() );
        txvOddForaSim.setText( wgtForaSim.getTextCotacao() );
        txvOddForaNao.setText( wgtForaNao.getTextCotacao() );
        txvOddEmpateSim.setText( wgtEmpateSim.getTextCotacao() );
        txvOddEmpateNao.setText( wgtEmpateNao.getTextCotacao() );
        return this;
    }
}
