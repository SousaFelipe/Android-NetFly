package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.DuplaChance;
import net.kingbets.cambista.view.widgets.Widget;


public class DuplaChanceView extends BaseOddsView {



    private DuplaChance duplaChance;


    private Widget wgtCasaEmpate;
    private Widget wgtForaEmpate;
    private Widget wgtCasaFora;

    private TextView txvOddCasaEmpate;
    private TextView txvOddForaEmpate;
    private TextView txvOddCasaFora;



    public DuplaChanceView(Context context, DuplaChance duplaChance) {
        super( LayoutInflater.from(context).inflate(R.layout.odds_dupla_chance, null, false) );

        setContext(context);
        setAposta(new Aposta(DuplaChance.TIPO).withPartida(duplaChance.partida));

        this.duplaChance = duplaChance;
    }


    @Override
    public DuplaChanceView create() {

        wgtCasaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_empate), duplaChance.casaEmpate);
        wgtForaEmpate = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_fora_empate), duplaChance.foraEmpate);
        wgtCasaFora   = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_casa_fora), duplaChance.casaFora);

        txvOddCasaEmpate = getRootView().findViewById(R.id.txv_odd_casa_empate);
        txvOddForaEmpate = getRootView().findViewById(R.id.txv_odd_fora_empate);
        txvOddCasaFora = getRootView().findViewById(R.id.txv_odd_casa_fora);

        return this;
    }


    @Override
    public DuplaChanceView build() {
        txvOddCasaEmpate.setText( wgtCasaEmpate.getTextCotacao() );
        txvOddForaEmpate.setText( wgtForaEmpate.getTextCotacao() );
        txvOddCasaFora.setText( wgtCasaFora.getTextCotacao() );
        return this;
    }



    public DuplaChanceView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
