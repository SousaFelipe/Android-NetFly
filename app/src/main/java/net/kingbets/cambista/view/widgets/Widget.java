package net.kingbets.cambista.view.widgets;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.PerfilContract;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.local.apostas.Single;

import java.util.Locale;


public class Widget implements View.OnClickListener {



    protected Context context;

    protected Aposta aposta;
    protected LinearLayout layout;

    protected TextView txvTitle;
              TextView txvOdd;

    private int backgroundResource;



    public Widget(View container) {

        this.context = container.getContext();
        this.layout = (LinearLayout) container;
        this.txvTitle = (TextView) this.layout.getChildAt(0);
        this.txvOdd = (TextView) this.layout.getChildAt(1);

        this.layout.setOnClickListener(this);
        this.backgroundResource = -1;
    }



    public Widget(Aposta aposta, View container, double cotacao) {

        this.context = container.getContext();

        this.aposta = aposta;
        this.aposta.cotacao = cotacao;

        this.layout = (LinearLayout) container;

        this.txvTitle = (TextView) this.layout.getChildAt(0);
        this.txvOdd = (TextView) this.layout.getChildAt(1);

        this.layout.setOnClickListener(this);
        this.backgroundResource = -1;

        verificaSelecao();
    }



    public void verificaSelecao() {

        Single single = Single.instance();

        if (single.mesmaPartida(aposta.partida) && single.mesmoWidget(this)) {
            mudaCor(true);
        }
    }



    public String getTitulo() {
        return aposta.titulo;
    }

    public void setTitulo(String titulo) {
        this.aposta.titulo = titulo;
    }



    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }



    public String getTextCotacao() {
        return String.format(Locale.getDefault(), "%.2f", aposta.cotacao) ;
    }

    public double getCotacao() {
        return aposta.cotacao;
    }

    public void setCotacao(double cotacao) {
        this.aposta.cotacao = cotacao;
    }



    public void setBackgroundResource(int backgroundResource) {
        this.backgroundResource = backgroundResource;
    }



    public void mostraCotacao() {
        this.txvOdd.setText( getTextCotacao() );
    }



    private void mudaCor(boolean select) {

        Resources res = layout.getContext().getResources();

        if (select) {

            if (backgroundResource != -1) {
                layout.setBackgroundResource(backgroundResource);
            }
            else {
                layout.setBackgroundColor(res.getColor(R.color.colorAccent));
            }

            txvTitle.setTextColor( res.getColor(R.color.textColorPrimary) );
            txvOdd.setTextColor( res.getColor(R.color.textColorPrimaryInverse) );
        }
        else {
            layout.setBackgroundColor(Color.TRANSPARENT);
            txvTitle.setTextColor( res.getColor(R.color.textColorSecondary) );
            txvOdd.setTextColor( res.getColor(R.color.textColorPrimary) );
        }
    }


    @Override
    public void onClick(View v) {

        Single single = Single.instance();

        if (single.mesmaPartida(aposta.partida)) {

            if (single.mesmoWidget(this)) {
                single.removeAposta(this);
                mudaCor(false);
            }
            else {
                Toast.makeText(layout.getContext(), "Não é possível realizar mais de uma aposta por partida!", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            single.addAposta(this);
            mudaCor(true);
        }

        PerfilContract contract = new PerfilContract( context );
        Single.instance().update( contract.first().limiteApostas );
    }


    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj != null)  {
            Widget out = ((Widget) obj);

            return (
                    aposta.id == out.getAposta().id &&
                    layout.getId() == out.layout.getId() &&
                    aposta.partida.equals(out.getAposta().partida)
            );
        }

        return false;
    }
}
