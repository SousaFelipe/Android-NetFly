package net.kingbets.cambista.view.widgets;


import android.view.View;

import net.kingbets.cambista.http.models.odds.principais.Placar;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.fragments.PartidasFragment;


public class WidgetOddPlacar extends WidgetOdd implements View.OnClickListener {



    private boolean enable;



    public WidgetOddPlacar(View container, BaseFragment parent) {
        super(container);
        this.enable = true;
        this.parent = (PartidasFragment) parent;
    }



    public void setTitulo(Placar placar) {

        String title = (placar.casa > placar.fora)
                ? placar.casa  + " x " + placar.fora
                : placar.fora  + " x " + placar.casa;

        txvTitulo.setText(title);
        txvOdd.setText( getTextOdd() );
    }


    @Override
    public void onClick(View v) {
        if (enable) {
            super.onClick(v);
        }
    }
}
