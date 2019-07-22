package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.DuplaChanceS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class DuplaChanceSView extends BaseOddsView {



    private DuplaChanceS duplaChanceS;


    private Widget wgtCasaEmpate;
    private Widget wgtForaEmpate;
    private Widget wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceSView(Context context, DuplaChanceS duplaChanceS) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance_st, null, false) );

        setContext(context);
        setAposta(new Aposta(DuplaChanceS.TIPO).withPartida(duplaChanceS.partida));

        this.duplaChanceS = duplaChanceS;
    }


    @Override
    public DuplaChanceSView create() {

        wgtCasaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_empate_st), duplaChanceS.casaEmpate);
        wgtForaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_empate_st), duplaChanceS.foraEmpate);
        wgtCasaFora   = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_fora_st), duplaChanceS.casaFora);

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceSView build() {
        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvOddCasaFora.setText( wgtCasaFora.getTextCotacao() );
        return this;
    }



    public DuplaChanceSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
