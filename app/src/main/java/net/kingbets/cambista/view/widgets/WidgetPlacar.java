package net.kingbets.cambista.view.widgets;


import android.view.View;

import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.Placar;


public class WidgetPlacar extends Widget implements View.OnClickListener {



    private boolean enable;



    public WidgetPlacar(Aposta aposta, View container, double cotacao) {
        super(aposta, container, cotacao);

        enable = true;
    }



    public void setTitulo(Placar placar) {
        super.setTitulo("Placar - Casa: " + placar.casa + " x Fora: " + placar.fora);

        String title = (placar.casa > placar.fora)
                ? placar.casa  + " x " + placar.fora
                : placar.fora  + " x " + placar.casa;

        txvTitle.setText(title);
        txvOdd.setText( getTextCotacao() );
    }


    @Override
    public void onClick(View v) {
        if (enable) {
            super.onClick(v);
        }
    }
}
