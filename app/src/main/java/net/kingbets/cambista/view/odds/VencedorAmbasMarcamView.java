package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
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
        setAposta(new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida));

        this.vencedorAmbasMarcam = vencedorAmbasMarcam;
    }


    @Override
    public VencedorAmbasMarcamView create() {

        Aposta casaS = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Casa x Ambas Marcam: SIM").sentenca("C;S").cotacao(vencedorAmbasMarcam.casaSim);
        Aposta casaN = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Casa x Ambas Marcam: NAO").sentenca("C;N").cotacao(vencedorAmbasMarcam.casaNao);
        Aposta foraS = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Fora x Ambas Marcam: SIM").sentenca("F;S").cotacao(vencedorAmbasMarcam.foraSim);
        Aposta foraN = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Fora x Ambas Marcam: NAO").sentenca("F;N").cotacao(vencedorAmbasMarcam.foraNao);
        Aposta empaS = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Empate x Ambas Marcam: SIM").sentenca("E;S").cotacao(vencedorAmbasMarcam.empateSim);
        Aposta empaN = new Aposta(VencedorAmbasMarcam.TIPO).partida(vencedorAmbasMarcam.partida).titulo("Empate x Ambas Marcam: NAO").sentenca("E;N").cotacao(vencedorAmbasMarcam.empateNao);

        wgtCasaSim = new Widget(casaS, getRootView().findViewById(R.id.layout_odd_casa_sim));
        wgtCasaNao = new Widget(casaN, getRootView().findViewById(R.id.layout_odd_casa_nao));
        wgtForaSim = new Widget(foraS, getRootView().findViewById(R.id.layout_odd_fora_sim));
        wgtForaNao = new Widget(foraN, getRootView().findViewById(R.id.layout_odd_fora_nao));
        wgtEmpateSim = new Widget(empaS, getRootView().findViewById(R.id.layout_odd_empate_sim));
        wgtEmpateNao = new Widget(empaN, getRootView().findViewById(R.id.layout_odd_empate_nao));

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
