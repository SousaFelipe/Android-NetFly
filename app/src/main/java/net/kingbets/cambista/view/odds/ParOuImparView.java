package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.ParImpar;
import net.kingbets.cambista.view.widgets.Widget;


public class ParOuImparView extends BaseOddsView {



    private ParImpar parOuImpar;

    private Widget wgtPar;
    private Widget wgtImpar;

    private TextView txvOddPar;
    private TextView txvOddImpar;



    public ParOuImparView(Context context, ParImpar parOuImpar) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_par_ou_impar, null, false));

        setContext(context);
        setAposta(new Aposta(ParImpar.TIPO).partida(parOuImpar.partida));

        this.parOuImpar = parOuImpar;
    }



    @Override
    public ParOuImparView create() {

        wgtPar = new Widget(getAposta("P"), getRootView().findViewById(R.id.layout_odd_par), parOuImpar.par);
        wgtPar.setTitulo("Par");

        wgtImpar = new Widget(getAposta("I"), getRootView().findViewById(R.id.layout_odd_impar), parOuImpar.impar);
        wgtImpar.setTitulo("Impar");

        txvOddPar = getRootView().findViewById(R.id.txv_odd_par);
        txvOddImpar = getRootView().findViewById(R.id.txv_odd_impar);

        return this;
    }


    @Override
    public ParOuImparView build() {
        txvOddPar.setText( wgtPar.getTextCotacao() );
        txvOddImpar.setText( wgtImpar.getTextCotacao() );
        return this;
    }



    public ParOuImparView withTitle(String title) {
        TextView txvTitle = getRootView().findViewById(R.id.txv_titulo);
        txvTitle.setText(title);
        return this;
    }
}
