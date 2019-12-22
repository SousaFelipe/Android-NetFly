package net.kingbets.cambista.view.odds.st;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.segundas.ParImparS;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ParOuImparSView extends BaseOddsView {



    private ParImparS parOuImparS;

    private WidgetOdd wgtPar;
    private WidgetOdd wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparSView(Context context, ParImparS parOuImparS) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar_st, null, false));
        setContext(context);
        this.parOuImparS = parOuImparS;
    }



    @Override
    public ParOuImparSView create(BaseFragment parent) {

        Bet par = new Bet(ParImparS.TIPO).odd(parOuImparS.id).partida(parOuImparS.partida).titulo("2° Tempo: Par").sentenca("P").cotacao(parOuImparS.par);
        Bet imp = new Bet(ParImparS.TIPO).odd(parOuImparS.id).partida(parOuImparS.partida).titulo("2° Tempo: Impar").sentenca("I").cotacao(parOuImparS.impar);

        wgtPar = new WidgetOdd(par, getRootView().findViewById(R.id.layout_odd_par_st), parent);
        wgtImpar = new WidgetOdd(imp, getRootView().findViewById(R.id.layout_odd_impar_st), parent);

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparSView build() {

        txvOddPar.setText( wgtPar.getTextOdd() );
        txvOddImpar.setText( wgtImpar.getTextOdd() );

        wgtPar.refresh();
        wgtImpar.refresh();

        return this;
    }



    public ParOuImparSView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
