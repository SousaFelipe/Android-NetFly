package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.odds.primeiras.ParImparP;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.WidgetOdd;


public class ParOuImparPView extends BaseOddsView {



    private ParImparP parOuImparP;

    private WidgetOdd wgtPar;
    private WidgetOdd wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparPView(Context context, ParImparP parOuImparP) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar_pt, null, false));
        setContext(context);
        this.parOuImparP = parOuImparP;
    }



    @Override
    public ParOuImparPView create(BaseFragment parent) {

        Bet par = new Bet(ParImparP.TIPO).odd(parOuImparP.id).partida(parOuImparP.partida).titulo("1° Tempo: Par").sentenca("P").cotacao(parOuImparP.par);
        Bet imp = new Bet(ParImparP.TIPO).odd(parOuImparP.id).partida(parOuImparP.partida).titulo("1° Tempo: Impar").sentenca("I").cotacao(parOuImparP.impar);

        wgtPar = new WidgetOdd(par, getRootView().findViewById(R.id.layout_odd_par_pt), parent);
        wgtImpar = new WidgetOdd(imp, getRootView().findViewById(R.id.layout_odd_impar_pt), parent);

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparPView build() {

        txvOddPar.setText( wgtPar.getTextOdd() );
        txvOddImpar.setText( wgtImpar.getTextOdd() );

        wgtPar.refresh();
        wgtImpar.refresh();

        return this;
    }



    public ParOuImparPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
