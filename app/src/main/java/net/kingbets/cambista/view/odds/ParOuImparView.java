package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.principais.ParImpar;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ParOuImparView extends BaseOddsView {



    private ParImpar parOuImpar;

    private WidgetOdd wgtPar;
    private WidgetOdd wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparView(Context context, ParImpar parOuImpar) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar, null, false));
        setContext(context);
        this.parOuImpar = parOuImpar;
    }



    @Override
    public ParOuImparView create(BaseFragment parent) {

        Bet par = new Bet(ParImpar.TIPO).odd(parOuImpar.id).partida(parOuImpar.partida).titulo("Par").sentenca("P").cotacao(parOuImpar.par);
        Bet imp = new Bet(ParImpar.TIPO).odd(parOuImpar.id).partida(parOuImpar.partida).titulo("Impar").sentenca("I").cotacao(parOuImpar.impar);

        wgtPar   = new WidgetOdd(par, getRootView().findViewById(R.id.layout_odd_par), parent);
        wgtImpar = new WidgetOdd(imp, getRootView().findViewById(R.id.layout_odd_impar), parent);

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparView build() {

        txvOddPar.setText( wgtPar.getTextOdd() );
        txvOddImpar.setText( wgtImpar.getTextOdd() );

        wgtPar.refresh();
        wgtImpar.refresh();

        return this;
    }



    public ParOuImparView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
