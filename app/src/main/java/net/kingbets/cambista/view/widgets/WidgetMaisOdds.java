package net.kingbets.cambista.view.widgets;


import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.http.models.futebol.Partida;
import net.kingbets.cambista.view.dialogs.DialogMaisOdds;
import net.kingbets.cambista.view.fragments.PartidasFragment;

import java.util.List;


public class WidgetMaisOdds implements View.OnClickListener {



    private String titulo;
    private Partida partida;
    private PartidasFragment parent;

    private FrameLayout layout;
    private ImageView icone;
    private int backgroundResource;



    public WidgetMaisOdds(View container) {
        this.layout = (FrameLayout) container;
        this.icone = (ImageView) layout.getChildAt(0);
        this.layout.setOnClickListener(this);
    }



    public String getTitulo() {
        return titulo;
    }

    public Partida getPartida() {
        return partida;
    }



    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }

    public void setBackgroundResource(int backgroundResource) {
        this.backgroundResource = backgroundResource;
    }



    public void refresh(List<WidgetOdd> mainOdds) {

        BetStack betStack = BetStack.instance();

        if (betStack.findMatch(partida.id)) {
            boolean selectOnMain = false;

            for (WidgetOdd wgt : mainOdds) {
                if (wgt.refresh()) {
                    selectOnMain = true;
                    break;
                }
            }

            select( !selectOnMain );
        }
        else {
            select(false);
        }
    }



    private void select(boolean select) {
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
        DialogMaisOdds.display(parent, getPartida());
    }
}
