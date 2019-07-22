package net.kingbets.cambista.view.widgets;


import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.apostas.Single;

import java.util.Locale;


public class Widget implements View.OnClickListener {



    private String id;
    private Aposta aposta;
    private double cotacao;

    private LinearLayout layout;
    private TextView txvTitle;
    private TextView txvOdd;



    public Widget(Aposta aposta, View container, double cotacao) {

        this.aposta = aposta;
        this.layout = (LinearLayout) container;
        this.cotacao = cotacao;

        this.txvTitle = (TextView) this.layout.getChildAt(0);
        this.txvOdd = (TextView) this.layout.getChildAt(1);

        this.layout.setOnClickListener(this);

        load();

        verificaSelecao();
    }



    private void verificaSelecao() {

        Single single = Single.instance();

        if (single.mesmaPartida(aposta.partida) && single.mesmoWidget(this)) {
            mudaCor(true);
        }
    }



    private void load() {
        this.aposta.hash = aposta.getHash(layout.getId(), txvTitle.getId(), txvOdd.getId());
        this.id = this.aposta.hash;
    }


    public String getId() {
        return id;
    }


    public Aposta getAposta() {
        return aposta;
    }


    public String getTextCotacao() {
        return String.format(Locale.getDefault(), "%.2f", cotacao) ;
    }



    private void mudaCor(boolean select) {

        Resources res = layout.getContext().getResources();

        if (select) {
            layout.setBackgroundColor( res.getColor(R.color.colorAccent ));
            txvTitle.setTextColor( res.getColor(R.color.textColorPrimary) );
            txvOdd.setTextColor( res.getColor(R.color.textColorPrimaryInverse) );
        }
        else {
            layout.setBackgroundColor(Color.WHITE);
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
    }


    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj != null)  {
            Widget other = ((Widget) obj);

            return (
                    other.getId().equals( this.getId() ) &&
                    other.getAposta().id == this.getAposta().id &&
                    other.getAposta().tipo.equals( this.getAposta().tipo ) &&
                    other.getTextCotacao().equals( this.getTextCotacao() )
            );
        }

        return false;
    }
}
