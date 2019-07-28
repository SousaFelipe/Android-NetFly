package net.kingbets.cambista.view.odds.pt;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.primeiras.ParImparP;
import net.kingbets.cambista.view.odds.BaseOddsView;
import net.kingbets.cambista.view.widgets.Widget;


public class ParOuImparPView extends BaseOddsView {



    private ParImparP parOuImparP;

    private Widget wgtPar;
    private Widget wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparPView(Context context, ParImparP parOuImparP) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar_pt, null, false));

        setContext(context);
        setAposta(new Aposta(ParImparP.TIPO).partida(parOuImparP.partida));

        this.parOuImparP = parOuImparP;
    }



    @Override
    public ParOuImparPView create() {

        Aposta par = new Aposta(ParImparP.TIPO).partida(parOuImparP.partida).titulo("1° Tempo: Par").sentenca("P").cotacao(parOuImparP.par);
        Aposta imp = new Aposta(ParImparP.TIPO).partida(parOuImparP.partida).titulo("1° Tempo: Impar").sentenca("I").cotacao(parOuImparP.impar);

        wgtPar = new Widget(par, getRootView().findViewById(R.id.layout_odd_par_pt));
        wgtImpar = new Widget(imp, getRootView().findViewById(R.id.layout_odd_impar_pt));

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparPView build() {
        txvOddPar.setText( wgtPar.getTextCotacao() );
        txvOddImpar.setText( wgtImpar.getTextCotacao() );
        return this;
    }



    public ParOuImparPView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
