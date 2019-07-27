package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.View;

import net.kingbets.cambista.model.local.apostas.Aposta;


public abstract class BaseOddsView {



    private Context context;
    private View rootView;
    private Aposta aposta;



    protected BaseOddsView(View rootView) {
        this.rootView = rootView;
    }



    public abstract BaseOddsView create();



    public abstract BaseOddsView build();



    protected Context getContext() {
        return context;
    }



    protected void setContext(Context context) {
        this.context = context;
    }



    public View getRootView() {
        return rootView;
    }



    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }



    public Aposta getAposta() {
        return aposta;
    }



    public Aposta getAposta(String sentenca) {
        aposta.sentenca = sentenca;
        return aposta;
    }

    public Aposta getAposta(String titulo, String sentenca, double cotacao) {
        Aposta copy = aposta;
        copy.titulo = titulo;
        copy.sentenca = sentenca;
        copy.cotacao = cotacao;
        return copy;
    }
}
