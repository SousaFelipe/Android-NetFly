package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.GolsMaisMenos;
import net.kingbets.cambista.view.widgets.Widget;


public class GolsMaisMenosView extends BaseOddsView {



    private GolsMaisMenos golsMaisMenos;

    private Widget wgtMais_05;
    private Widget wgtMenos_05;
    private Widget wgtMais_15;
    private Widget wgtMenos_15;
    private Widget wgtMais_25;
    private Widget wgtMenos_25;
    private Widget wgtMais_35;
    private Widget wgtMenos_35;
    private Widget wgtMais_45;
    private Widget wgtMenos_45;
    private Widget wgtMais_55;
    private Widget wgtMenos_55;

    private TextView txvOddMais_05;
    private TextView txvOddMenos_05;
    private TextView txvOddMais_15;
    private TextView txvOddMenos_15;
    private TextView txvOddMais_25;
    private TextView txvOddMenos_25;
    private TextView txvOddMais_35;
    private TextView txvOddMenos_35;
    private TextView txvOddMais_45;
    private TextView txvOddMenos_45;
    private TextView txvOddMais_55;
    private TextView txvOddMenos_55;



    public GolsMaisMenosView(Context context, GolsMaisMenos golsMaisMenos) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_gols_mais_menos, null, false));

        setContext(context);
        setAposta(new Aposta(GolsMaisMenos.TIPO).withPartida(golsMaisMenos.partida));

        this.golsMaisMenos = golsMaisMenos;
    }



    @Override
    public GolsMaisMenosView create() {

        wgtMais_05 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_05), golsMaisMenos.mais_05);
        wgtMenos_05 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_05), golsMaisMenos.menos_05);
        wgtMais_15 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_15), golsMaisMenos.mais_15);
        wgtMenos_15 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_15), golsMaisMenos.menos_15);
        wgtMais_25 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_25), golsMaisMenos.mais_25);
        wgtMenos_25 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_25), golsMaisMenos.menos_25);
        wgtMais_35 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_35), golsMaisMenos.mais_35);
        wgtMenos_35 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_35), golsMaisMenos.menos_35);
        wgtMais_45 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_45), golsMaisMenos.mais_45);
        wgtMenos_45 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_45), golsMaisMenos.menos_45);
        wgtMais_55 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_mais_55), golsMaisMenos.mais_55);
        wgtMenos_55 = new Widget(getAposta(), getRootView().findViewById(R.id.layout_odd_menos_55), golsMaisMenos.menos_55);

        txvOddMais_05 = getRootView().findViewById(R.id.txv_odd_mais_05);
        txvOddMenos_05 = getRootView().findViewById(R.id.txv_odd_menos_05);
        txvOddMais_15 = getRootView().findViewById(R.id.txv_odd_mais_15);
        txvOddMenos_15 = getRootView().findViewById(R.id.txv_odd_menos_15);
        txvOddMais_25 = getRootView().findViewById(R.id.txv_odd_mais_25);
        txvOddMenos_25 = getRootView().findViewById(R.id.txv_odd_menos_25);
        txvOddMais_35 = getRootView().findViewById(R.id.txv_odd_mais_35);
        txvOddMenos_35 = getRootView().findViewById(R.id.txv_odd_menos_35);
        txvOddMais_45 = getRootView().findViewById(R.id.txv_odd_mais_45);
        txvOddMenos_45 = getRootView().findViewById(R.id.txv_odd_menos_45);
        txvOddMais_55 = getRootView().findViewById(R.id.txv_odd_mais_55);
        txvOddMenos_55 = getRootView().findViewById(R.id.txv_odd_menos_55);

        return this;
    }


    @Override
    public GolsMaisMenosView build() {
        txvOddMais_05.setText( wgtMais_05.getTextCotacao() );
        txvOddMenos_05.setText( wgtMenos_05.getTextCotacao() );
        txvOddMais_15.setText( wgtMais_15.getTextCotacao() );
        txvOddMenos_15.setText( wgtMenos_15.getTextCotacao() );
        txvOddMais_25.setText( wgtMais_25.getTextCotacao() );
        txvOddMenos_25.setText( wgtMenos_25.getTextCotacao() );
        txvOddMais_35.setText( wgtMais_35.getTextCotacao() );
        txvOddMenos_35.setText( wgtMenos_35.getTextCotacao() );
        txvOddMais_45.setText( wgtMais_45.getTextCotacao() );
        txvOddMenos_45.setText( wgtMenos_45.getTextCotacao() );
        txvOddMais_55.setText( wgtMais_55.getTextCotacao() );
        txvOddMenos_55.setText( wgtMenos_55.getTextCotacao() );
        return this;
    }
}
