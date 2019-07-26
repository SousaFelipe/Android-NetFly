package net.kingbets.cambista.view.widgets;


import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.apostas.Single;
import net.kingbets.cambista.model.remote.futebol.Partida;
import net.kingbets.cambista.model.remote.odds.principais.Resultado;
import net.kingbets.cambista.view.dialogs.MaisOddsDialog;
import net.kingbets.cambista.view.fragments.PartidasFragment;


public class MaisOddsWidget implements View.OnClickListener {



    private String titulo;
    private Partida partida;
    private PartidasFragment parent;

    private FrameLayout layout;
    private ImageView icone;

    private int backgroundResource;



    public MaisOddsWidget(View container) {
        this.layout = (FrameLayout) container;
        this.icone = (ImageView) layout.getChildAt(0);
        this.layout.setOnClickListener(this);
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    public void verificaSelecao() {

        Single single = Single.instance();

        for (Widget w : single.widgets) {

            Aposta aposta = w.getAposta();

            if (aposta.partida.equals(getPartida())) {
                if (aposta.tipo.equals(Resultado.TIPO)) {
                    mudaCor(false);
                    break;
                }
                else {
                    mudaCor(true);
                    break;
                }
            }
        }
    }



    public void setBackgroundResource(int backgroundResource) {
        this.backgroundResource = backgroundResource;
    }



    private void mudaCor(boolean select) {
        if (select) {
            layout.setBackgroundResource(backgroundResource);
            icone.setImageResource(R.drawable.ic_mais_odds_white);
        }
        else {
            layout.setBackgroundColor(Color.TRANSPARENT);
            icone.setImageResource(R.drawable.ic_mais_odds_dark);
        }
    }


    @Override
    public void onClick(View v) {
        MaisOddsDialog.display( parent, getPartida());
    }
}
