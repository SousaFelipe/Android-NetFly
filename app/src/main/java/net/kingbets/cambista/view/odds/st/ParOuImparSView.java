package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.segundas.ParImparS;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class ParOuImparSView extends BaseOddsView {



    private ParImparS parOuImparS;

    private Widget wgtPar;
    private Widget wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparSView(Context context, ParImparS parOuImparS) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar_st, null, false));

        setContext(context);
        setAposta(new Aposta(ParImparS.TIPO).partida(parOuImparS.partida));

        this.parOuImparS = parOuImparS;
    }



    @Override
    public ParOuImparSView create() {

        Aposta par = new Aposta(ParImparS.TIPO).partida(parOuImparS.partida).titulo("2° Tempo: Par").sentenca("P").cotacao(parOuImparS.par);
        Aposta imp = new Aposta(ParImparS.TIPO).partida(parOuImparS.partida).titulo("2° Tempo: Impar").sentenca("I").cotacao(parOuImparS.impar);

        wgtPar = new Widget(par, getRootView().findViewById(R.id.layout_odd_par_st));
        wgtImpar = new Widget(imp, getRootView().findViewById(R.id.layout_odd_impar_st));

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparSView build() {
        txvOddPar.setText( wgtPar.getTextCotacao() );
        txvOddImpar.setText( wgtImpar.getTextCotacao() );
        return this;
    }



    public ParOuImparSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
