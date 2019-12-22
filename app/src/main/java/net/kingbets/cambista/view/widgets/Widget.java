package net.kingbets.cambista.view.widgets;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.responses.ApostaResponse;

import java.util.Locale;


public class Widget {



    protected Bet bet;
    protected Context context;

    TextView txvTitulo;
    TextView txvOdd;



    public String getTextOdd() {
        return String.format(Locale.getDefault(), "%.2f", bet.cotacao);
    }



    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    void setTxvTitulo(View txvTitulo) {
        this.txvTitulo = (TextView) txvTitulo;
    }

    void setTxvOdd(View txvOdd) {
        this.txvOdd = (TextView) txvOdd;
    }



    void displayTitulo() {
        txvTitulo.setText(bet.titulo);
    }

    void displayOdd() {
        txvOdd.setText(String.format(Locale.getDefault(), "%.2f", bet.cotacao));
    }



    void showResponse(@Nullable FragmentActivity activity, ApostaResponse.Status status) {

        String msg = "";

        switch (status) {

            case APOSTA_N_EXISTE:
                msg = "Não foi possível encontrar a aposta!";
                break;

            case PARTIDA_DUPLICADA:
                msg = "Não é permitido realizar mais de uma aposta na mesma partida!";
                break;

            case DESCONHECIDO:
                msg = "O servidor retornou um resposta desconhecida!";
                break;
        }

        final String fnMsg = msg;

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override public void run() {
                    Toast.makeText(context, fnMsg, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
